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
import com.andyapps.enrow.presentation.ui.feature.habit.check.HabitCheckEvent
import com.andyapps.enrow.presentation.ui.feature.habit.check.HabitCheckUiModel
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreenEvent
import com.andyapps.enrow.presentation.ui.feature.habit.form.HabitFormEvent
import com.andyapps.enrow.presentation.ui.feature.habit.form.HabitFormUiModel
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitListItemUiModel
import com.andyapps.enrow.presentation.ui.feature.navigation.NavigationEvent
import com.andyapps.enrow.presentation.ui.feature.navigation.Route
import com.andyapps.enrow.presentation.ui.feature.toast.ToastEvent
import com.andyapps.enrow.presentation.ui.shared.DomainHelper
import com.andyapps.enrow.presentation.ui.shared.ViewModel
import com.andyapps.enrow.shared.Res
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val getAllHabitsUseCase: GetAllHabitsUseCase,
    private val createHabitUseCase: CreateHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val getHabitByIdUseCase: GetHabitByIdUseCase,
    private val checkHabitUseCase: CheckHabitUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HabitState())
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
                        habits = habits.map { habit -> HabitListItemUiModel.createFromDto(habit) }
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
                            habitForm = HabitFormUiModel.Create.default()
                        )
                    }
                    navigateInScope(NavigationEvent.NavigateToRoute(Route.HabitForm.name))
                }
            }

            is HabitScreenEvent.SelectHabit -> {
                launchIn {
                    fetchAndUpdateHabitCheck(event.id)

                    navigateInScope(NavigationEvent.NavigateToRoute(Route.HabitCheck.name))
                }
            }
        }
    }

    fun onModifyEvent(event: HabitFormEvent) {
        when (event) {
            is HabitFormEvent.Create -> {
                val habit = Habit.create(event.name, DomainHelper.convertToCheckInDays(event.checkInDays))

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

            is HabitFormEvent.Update -> {
                val habit = Habit.create(event.name, DomainHelper.convertToCheckInDays(event.checkInDays))

                launchIn {
                    when (val res = updateHabitUseCase.execute(event.id, habit)) {
                        is Res.Error -> {
                            when (res.error) {
                                UseCaseError.UpdateHabit.NOT_FOUND -> {
                                    showToastInScope(ToastEvent.Show("Habit not found!"))
                                }
                            }
                        }
                        is Res.Success -> {
                            loadAllHabits()

                            fetchAndUpdateHabitCheck(res.data.id.value)

                            navigateInScope(NavigationEvent.NavigateUp)
                        }
                    }
                }
            }

            is HabitFormEvent.Delete -> {
                launchIn {
                    when (val res = deleteHabitUseCase.execute(event.id)) {
                        is Res.Error -> TODO()
                        is Res.Success -> {
                            navigateInScope(NavigationEvent.NavigateInclusive(Route.HabitScreen.name))
                        }
                    }
                }
            }

            HabitFormEvent.Cancel -> {
                navigate(NavigationEvent.NavigateUp)
            }
        }
    }

    fun onCheckEvent(event: HabitCheckEvent) {
        when (event) {
            is HabitCheckEvent.Check -> {
                launchIn {
                    when (val res = checkHabitUseCase.execute(event.id)) {
                        is Res.Error -> {
                            when (res.error) {
                                UseCaseError.CheckHabit.NOT_FOUND -> {
                                    showToastInScope(ToastEvent.Show("Habit not found!"))
                                }
                                UseCaseError.CheckHabit.ALREADY_CHECKED -> {
                                    showToastInScope(ToastEvent.Show("Habit already checked in! Wait for tomorrow!"))
                                }
                                UseCaseError.CheckHabit.EXPIRED -> {
                                    showToastInScope(ToastEvent.Show("Habit is expired!"))
                                }

                                UseCaseError.CheckHabit.CAN_NOT_GET_DAY_OF_A_WEEK -> showToastInScope(ToastEvent.Show("Can't get day of a week from habit."))
                                UseCaseError.CheckHabit.WRONG_DAY_OF_A_WEEK -> showToastInScope(ToastEvent.Show("Today is not about this habit!"))
                            }
                        }
                        is Res.Success -> {
                            fetchAndUpdateHabitCheck(event.id)
                            loadAllHabits()
                        }
                    }
                }
            }
            HabitCheckEvent.Close -> {
                navigate(NavigationEvent.NavigateUp)
            }
            is HabitCheckEvent.Abort -> TODO()
            is HabitCheckEvent.Edit -> {
                launchIn {
                    getHabitByIdUseCase.execute(event.id)?.let { habit ->
                        _state.update {
                            it.copy(
                                habitForm = HabitFormUiModel.Update.fromDto(habit)
                            )
                        }

                        navigateInScope(NavigationEvent.NavigateToRoute(Route.HabitForm.name))
                    }
                }
            }
        }
    }

    private suspend fun fetchAndUpdateHabitCheck(id: UUID) {
        getHabitByIdUseCase.execute(id)?.let { habit ->
            _state.update {
                it.copy(
                    habitCheck = HabitCheckUiModel.createFromDto(habit)
                )
            }
        }
    }
}