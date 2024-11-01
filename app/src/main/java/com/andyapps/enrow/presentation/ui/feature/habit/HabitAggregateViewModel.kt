package com.andyapps.enrow.presentation.ui.feature.habit

import androidx.lifecycle.viewModelScope
import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.application.usecase.CheckHabitUseCase
import com.andyapps.enrow.application.usecase.CreateHabitUseCase
import com.andyapps.enrow.application.usecase.DeleteHabitUseCase
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import com.andyapps.enrow.application.usecase.GetHabitByIdUseCase
import com.andyapps.enrow.application.usecase.UpdateHabitUseCase
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.presentation.ui.feature.habit.check.CheckHabitEvent
import com.andyapps.enrow.presentation.ui.feature.habit.menu.HabitScreenEvent
import com.andyapps.enrow.presentation.ui.feature.habit.modify.ModifyHabitEvent
import com.andyapps.enrow.presentation.ui.feature.navigation.NavigationEvent
import com.andyapps.enrow.presentation.ui.feature.navigation.Route
import com.andyapps.enrow.presentation.ui.feature.toast.ToastEvent
import com.andyapps.enrow.presentation.ui.shared.ViewModel
import com.andyapps.enrow.shared.Res
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HabitAggregateViewModel @Inject constructor(
    private val getAllHabitsUseCase: GetAllHabitsUseCase,
    private val createHabitUseCase: CreateHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val getHabitByIdUseCase: GetHabitByIdUseCase,
    private val checkHabitUseCase: CheckHabitUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HabitAggregateState())
    val state = _state.asStateFlow()

    private var loadAllHabitsJob: Job? = null

    init {
        loadAllHabits()
    }

    private fun loadAllHabits() {
        loadAllHabitsJob?.cancel()

        loadAllHabitsJob = getAllHabitsUseCase.execute()
            .onEach { habits ->
                _state.update {
                    it.copy(
                        habits = habits
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onScreenEvent(event: HabitScreenEvent) {
        when (event) {
            HabitScreenEvent.AddHabit -> {
                launchIn {
                    _state.update {
                        it.copy(
                            selectedHabit = null
                        )
                    }
                    navigateInScope(NavigationEvent.NavigateToRoute(Route.ModifyHabit.name))
                }
            }

            is HabitScreenEvent.SelectHabit -> {
                launchIn {
                    fetchAndUpdateSelectedHabit(event.id)

                    navigateInScope(NavigationEvent.NavigateToRoute(Route.CheckHabit.name))
                }
            }
        }
    }

    fun onModifyEvent(event: ModifyHabitEvent) {
        when (event) {
            is ModifyHabitEvent.Create -> {
                val habit = Habit.create(event.name)

                launchIn {
                    when (val res = createHabitUseCase.execute(habit)) {
                        is Res.Error -> {
                            when (res.error) {
                                UseCaseError.CreateHabit.ALREADY_EXISTS_BY_ID -> showToastInScope(ToastEvent.Show("A habit with the same id is already exists!"))
                                UseCaseError.CreateHabit.ALREADY_EXISTS_BY_NAME -> showToastInScope(ToastEvent.Show("A habit with the same name is already exists!"))
                            }
                        }
                        is Res.Success -> {
                            loadAllHabits()

                            navigateInScope(NavigationEvent.NavigateUp)
                        }
                    }

                }

            }

            is ModifyHabitEvent.Update -> {
                val habit = Habit.create(event.name)

                launchIn {
                    when (val res = updateHabitUseCase.execute(event.id, habit)) {
                        is Res.Error -> TODO()
                        is Res.Success -> {
                            loadAllHabits()

                            fetchAndUpdateSelectedHabit(res.data.id.value)

                            navigateInScope(NavigationEvent.NavigateUp)
                        }
                    }
                }
            }

            is ModifyHabitEvent.Delete -> {
                launchIn {
                    when (val res = deleteHabitUseCase.execute(event.id)) {
                        is Res.Error -> TODO()
                        is Res.Success -> {
                            navigateInScope(NavigationEvent.NavigateInclusive(Route.HabitScreen.name))
                        }
                    }
                }
            }

            ModifyHabitEvent.Cancel -> {
                navigate(NavigationEvent.NavigateUp)
            }
        }
    }

    fun onCheckEvent(event: CheckHabitEvent) {
        when (event) {
            is CheckHabitEvent.Check -> {
                launchIn {
                    when (val res = checkHabitUseCase.execute(event.id)) {
                        is Res.Error -> {
                            when (res.error) {
                                UseCaseError.CheckHabit.NOT_FOUND -> {
                                    showToastInScope(ToastEvent.Show("Habit not found!"))
                                }
                                UseCaseError.CheckHabit.JUST_CREATED -> {
                                    showToastInScope(ToastEvent.Show("Habit just created! Wait for tomorrow!"))
                                }
                                UseCaseError.CheckHabit.ALREADY_CHECKED -> {
                                    showToastInScope(ToastEvent.Show("Habit already created! Wait for tomorrow!"))
                                }
                                UseCaseError.CheckHabit.EXPIRED -> {
                                    showToastInScope(ToastEvent.Show("Habit is expired!"))
                                }
                            }
                        }
                        is Res.Success -> {
                            navigate(NavigationEvent.NavigateUp)
                        }
                    }
                }
            }
            CheckHabitEvent.Close -> {
                navigate(NavigationEvent.NavigateUp)
            }
            is CheckHabitEvent.Abort -> TODO()
            is CheckHabitEvent.Edit -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getHabitByIdUseCase.execute(event.id)?.let { habit ->
                        _state.update {
                            it.copy(
                                selectedHabit = habit
                            )
                        }

                        navigateInScope(NavigationEvent.NavigateToRoute(Route.ModifyHabit.name))
                    }
                }
            }
        }
    }

    private suspend fun fetchAndUpdateSelectedHabit(id: UUID) {
        getHabitByIdUseCase.execute(id)?.let { habit ->
            _state.update {
                it.copy(
                    selectedHabit = habit.copy()
                )
            }
        }
    }
}