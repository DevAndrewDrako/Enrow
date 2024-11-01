package com.andyapps.enrow.application.dto

import com.andyapps.enrow.data.entity.HabitLogEntity
import com.andyapps.enrow.data.entity.HabitWithLogs
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.enumeration.HabitEventType
import java.util.Calendar
import java.util.UUID

fun HabitLogEntity.asDto(name: String) : HabitLogDto {
    return HabitLogDto(
        habitName = name,
        eventType = HabitEventType.entries.find { it.ordinal == eventType }
            ?: HabitEventType.UNDEFINED,
        description = description,
        createdAt = Calendar.getInstance().apply { timeInMillis = createdAt }
    )
}

fun HabitWithLogs.asDto() : HabitDto {
    return HabitDto(
        id = UUID.fromString(habit.id),
        name = habit.name,
        checkIns = logs.filter { it.eventType == HabitEventType.CHECKED_IN.ordinal }.map { Calendar.getInstance().apply { timeInMillis = it.createdAt } }
    )
}