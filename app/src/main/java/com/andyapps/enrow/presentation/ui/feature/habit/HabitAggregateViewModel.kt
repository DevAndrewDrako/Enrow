package com.andyapps.enrow.presentation.ui.feature.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.dto.asDto
import com.andyapps.enrow.application.usecase.CreateHabitUseCase
import com.andyapps.enrow.application.usecase.DeleteHabitUseCase
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import com.andyapps.enrow.application.usecase.GetHabitByIdUseCase
import com.andyapps.enrow.application.usecase.UpdateHabitUseCase
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.presentation.ui.feature.habit.check.CheckHabitEvent
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreenEvent
import com.andyapps.enrow.presentation.ui.feature.habit.modify.ModifyHabitEvent
import com.andyapps.enrow.presentation.ui.feature.navigation.NavigationEvent
import com.andyapps.enrow.presentation.ui.feature.navigation.Route
import com.andyapps.enrow.presentation.ui.shared.HabitToModify
import com.andyapps.enrow.shared.Res
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitAggregateViewModel @Inject constructor(
    private val getAllHabitsUseCase: GetAllHabitsUseCase,
    private val createHabitUseCase: CreateHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val getHabitByIdUseCase: GetHabitByIdUseCase
) : ViewModel() {

    private val _navigationChannel = Channel<NavigationEvent>()
    val navigationFlow = _navigationChannel.receiveAsFlow()

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
                viewModelScope.launch(Dispatchers.IO) {
                    _state.update {
                        it.copy(
                            modifyingHabit = HabitToModify()
                        )
                    }
                    _navigationChannel.send(NavigationEvent.NavigateToRoute(Route.ModifyHabit.name))
                }
            }

            is HabitScreenEvent.SelectHabit -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getHabitByIdUseCase.execute(event.id)?.let { habit ->
                        _state.update {
                            it.copy(
                                displayingHabit = HabitDto(
                                    id = habit.id,
                                    name = habit.name,
                                    daysInRow = habit.daysInRow()
                                )
                            )
                        }

                        _navigationChannel.send(NavigationEvent.NavigateToRoute(Route.CheckHabit.name))
                    }
                }
            }
        }
    }

    fun onModifyEvent(event: ModifyHabitEvent) {
        when (event) {
            is ModifyHabitEvent.Create -> {
                val habit = Habit.create(event.name)

                viewModelScope.launch(Dispatchers.IO) {
                    when (val res = createHabitUseCase.execute(habit)) {
                        is Res.Error -> TODO()
                        is Res.Success -> {
                            loadAllHabits()

                            _navigationChannel.send(NavigationEvent.NavigateUp)
                        }
                    }

                }

            }

            is ModifyHabitEvent.Update -> {
                val habit = Habit.create(event.name)

                viewModelScope.launch(Dispatchers.IO) {
                    when (val res = updateHabitUseCase.execute(event.id, habit)) {
                        is Res.Error -> TODO()
                        is Res.Success -> {
                            loadAllHabits()

                            _state.update {
                                it.copy(
                                    displayingHabit = res.data.asDto()
                                )
                            }

                            _navigationChannel.send(NavigationEvent.NavigateUp)
                        }
                    }
                }
            }

            is ModifyHabitEvent.Delete -> {
                viewModelScope.launch(Dispatchers.IO) {
                    when (val res = deleteHabitUseCase.execute(event.id)) {
                        is Res.Error -> TODO()
                        is Res.Success -> {
                            _navigationChannel.send(NavigationEvent.NavigateInclusive(Route.HabitScreen.name))
                        }
                    }
                }
            }
        }
    }

    fun onCheckEvent(event: CheckHabitEvent) {
        when (event) {
            is CheckHabitEvent.Check -> {

            }
            CheckHabitEvent.Close -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _navigationChannel.send(NavigationEvent.NavigateUp)
                }
            }
            is CheckHabitEvent.Delete -> TODO()
            is CheckHabitEvent.Edit -> {
                viewModelScope.launch(Dispatchers.IO) {
                    getHabitByIdUseCase.execute(event.id)?.let { habit ->
                        _state.update {
                            it.copy(
                                modifyingHabit = HabitToModify(
                                    id = habit.id,
                                    name = habit.name
                                )
                            )
                        }

                        _navigationChannel.send(NavigationEvent.NavigateToRoute(Route.ModifyHabit.name))
                    }
                }
            }
        }
    }
}