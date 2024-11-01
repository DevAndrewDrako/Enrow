package com.andyapps.enrow.application.dto

import com.andyapps.enrow.domain.enumeration.HabitEventType
import java.util.Calendar

data class HabitLogDto(
    val habitName: String,
    val eventType: HabitEventType,
    val description: String,
    val createdAt: Calendar
)
