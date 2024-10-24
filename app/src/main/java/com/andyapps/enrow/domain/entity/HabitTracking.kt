package com.andyapps.enrow.domain.entity

import com.andyapps.enrow.domain.enumeration.HabitAction
import java.util.Calendar
import java.util.UUID

class HabitTracking private constructor(
    val id: UUID,
    val habitId: UUID,
    val habitAction: HabitAction,
    val createdAt: Calendar
) {
    companion object {
        fun create(habitId: UUID, habitAction: HabitAction) : HabitTracking {
            return HabitTracking(
                id = UUID.randomUUID(),
                habitId = habitId,
                habitAction = habitAction,
                createdAt = Calendar.getInstance()
            )
        }
    }
}