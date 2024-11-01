package com.andyapps.enrow.presentation.ui.feature.admin.log.habit

import com.andyapps.enrow.application.dto.HabitLogDto

data class HabitLogScreenState(
    val logs: List<HabitLogDto> = emptyList()
)
