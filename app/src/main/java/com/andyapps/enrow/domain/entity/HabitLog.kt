package com.andyapps.enrow.domain.entity

import com.andyapps.enrow.domain.enumeration.HabitEventType
import java.util.Calendar
import java.util.UUID

class HabitLog private constructor(
    val id: UUID,
    val habitId: UUID,
    val eventType: HabitEventType,
    val description: String,
    val createdAt: Calendar
) {
    companion object {
        fun create(habitId: UUID, eventType: HabitEventType, description: String = "") : HabitLog {
            return HabitLog(
                id = UUID.randomUUID(),
                habitId = habitId,
                eventType = eventType,
                createdAt = Calendar.getInstance(),
                description = description
            )
        }
    }
}