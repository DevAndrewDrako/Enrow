package com.andyapps.enrow.presentation.ui.feature.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreenEvent
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreenState
import com.andyapps.enrow.presentation.ui.feature.habit.modify.HabitModifyPageState
import com.andyapps.enrow.presentation.ui.feature.navigation.NavigateToRoute
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
    private val getAllHabitsUseCase: GetAllHabitsUseCase
) : ViewModel() {

    private val _navigationChannel = Channel<NavigateToRoute>()
    val navigationFlow = _navigationChannel.receiveAsFlow()

    private val _listState = MutableStateFlow(HabitScreenState())
    val listState = _listState.asStateFlow()

    private val _modifyState = MutableStateFlow(HabitModifyPageState())
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
                    _navigationChannel.send(NavigateToRoute("habit_modify"))
                }
            }

            is HabitScreenEvent.SelectHabit -> {

            }
        }
    }
}