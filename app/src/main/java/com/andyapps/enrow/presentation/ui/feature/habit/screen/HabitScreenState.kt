package com.andyapps.enrow.presentation.ui.feature.habit.screen

import com.andyapps.enrow.application.dto.HabitDto

data class HabitScreenState(
    val habits: List<HabitDto> = emptyList()
)
