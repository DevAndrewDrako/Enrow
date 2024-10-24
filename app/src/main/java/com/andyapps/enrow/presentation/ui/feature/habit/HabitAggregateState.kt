package com.andyapps.enrow.presentation.ui.feature.habit

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.presentation.ui.shared.HabitToModify

data class HabitAggregateState(
    val habits: List<HabitDto> = emptyList(),
    val modifyingHabit: HabitToModify = HabitToModify(),
    val displayingHabit: HabitDto? = null
)