package com.andyapps.enrow.domain.entity

import java.util.Calendar
import java.util.UUID

class HabitTracking(
    val id: UUID,
    val habitId: UUID,
    val isChecked: Boolean,
    val checkedAt: Calendar
) {
}