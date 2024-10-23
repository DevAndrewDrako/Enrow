package com.andyapps.enrow.presentation.ui.feature.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import com.andyapps.enrow.presentation.ui.feature.habit.screen.HabitScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HabitAggregateViewModel @Inject constructor(
    private val getAllHabitsUseCase: GetAllHabitsUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(HabitScreenState())
    val screenState = _screenState.asStateFlow()

    private var loadAllHabitsJob: Job? = null

    private fun loadAllHabits() {
        loadAllHabitsJob?.cancel()

        loadAllHabitsJob = getAllHabitsUseCase.execute()
            .onEach { habits ->
                _screenState.update {
                    it.copy(
                        habits = habits
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}