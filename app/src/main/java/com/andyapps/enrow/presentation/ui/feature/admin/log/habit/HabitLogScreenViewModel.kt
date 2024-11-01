package com.andyapps.enrow.presentation.ui.feature.admin.log.habit

import androidx.lifecycle.viewModelScope
import com.andyapps.enrow.application.usecase.GetHabitLogsUseCase
import com.andyapps.enrow.presentation.ui.shared.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HabitLogScreenViewModel @Inject constructor(
    private val getHabitLogsUseCase: GetHabitLogsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HabitLogScreenState())
    val state = _state.asStateFlow()

    private var fetchLogsJob: Job? = null

    init {
        fetchLogs()
    }

    private fun fetchLogs() {
        fetchLogsJob?.cancel()

        fetchLogsJob = getHabitLogsUseCase.execute()
            .onEach { trackings ->
                _state.update {
                    it.copy(
                        logs = trackings
                    )
                }
            }
            .launchIn(viewModelScope)

    }

    fun onEvent(event: HabitLogScreenEvent) {

    }
}