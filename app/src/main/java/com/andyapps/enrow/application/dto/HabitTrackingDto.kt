package com.andyapps.enrow.application.dto

import com.andyapps.enrow.domain.enumeration.HabitAction
import java.util.UUID

data class HabitTrackingDto(
    val id: UUID,
    val habitId: UUID,
    val habitAction: HabitAction,
)
