package com.andyapps.enrow.presentation.ui.feature.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyapps.enrow.application.usecase.CreateHabitUseCase
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreenEvent
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreenState
import com.andyapps.enrow.presentation.ui.feature.habit.modify.ModifyHabitState
import com.andyapps.enrow.presentation.ui.feature.habit.modify.ModifyHabitEvent
import com.andyapps.enrow.presentation.ui.feature.navigation.NavigationEvent
import com.andyapps.enrow.presentation.ui.feature.navigation.Route
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
    private val createHabitUseCase: CreateHabitUseCase
) : ViewModel() {

    private val _navigationChannel = Channel<NavigationEvent>()
    val navigationFlow = _navigationChannel.receiveAsFlow()

    private val _listState = MutableStateFlow(HabitScreenState())
    val listState = _listState.asStateFlow()

    private val _modifyState = MutableStateFlow(ModifyHabitState())
    val modifyState = _modifyState.asStateFlow()

    private var loadAllHabitsJob: Job? = null

    init {
        loadAllHabits()
    }

    private fun loadAllHabits() {
        loadAllHabitsJob?.cancel()

        loadAllHabitsJob = getAllHabitsUseCase.execute()
            .onEach { habits ->
                _listState.update {
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
                    _navigationChannel.send(NavigationEvent.NavigateToRoute(Route.ModifyHabit.name))
                }
            }

            is HabitScreenEvent.SelectHabit -> {

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

                            _navigationChannel.send(NavigationEvent.NavigateInclusive(Route.HabitScreen.name))
                        }
                    }

                }

            }
        }
    }
}