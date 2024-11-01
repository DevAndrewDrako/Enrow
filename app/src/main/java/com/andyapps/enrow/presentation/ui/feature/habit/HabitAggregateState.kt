package com.andyapps.enrow.presentation.ui.feature.habit

import com.andyapps.enrow.application.dto.HabitDto

data class HabitAggregateState(
    val habits: List<HabitDto> = emptyList(),
    val selectedHabit: HabitDto? = null
)