package com.andyapps.enrow.application.dto

import java.util.UUID

data class HabitDto(
    val id: UUID,
    val name: String,
    val latestTrackings: List<HabitTrackingDto>,
    val daysInRow: Int
)
