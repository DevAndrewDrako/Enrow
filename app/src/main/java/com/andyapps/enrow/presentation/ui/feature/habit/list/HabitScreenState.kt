package com.andyapps.enrow.presentation.ui.feature.habit.list

import com.andyapps.enrow.application.dto.HabitDto

data class HabitScreenState(
    val habits: List<HabitDto> = emptyList()
)
