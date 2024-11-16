package com.andyapps.enrow.application.dto

import com.andyapps.enrow.data.entity.HabitLogEntity
import com.andyapps.enrow.data.entity.HabitWithLogs
import com.andyapps.enrow.domain.enumeration.HabitEventType
import com.andyapps.enrow.shared.toLocalDateTime
import java.util.Calendar
import java.util.UUID

fun HabitLogEntity.asDto(name: String) : HabitLogDto {
    return HabitLogDto(
        habitName = name,
        eventType = HabitEventType.entries.find { it.ordinal == eventType }
            ?: HabitEventType.UNDEFINED,
        description = description,
        createdAt = createdAt.toLocalDateTime()
    )
}

fun HabitWithLogs.asDto() : HabitDto {
    return HabitDto(
        id = UUID.fromString(habit.id),
        name = habit.name,
        checkIns = logs.filter { it.eventType == HabitEventType.CHECKED_IN.ordinal }.map { it.createdAt.toLocalDateTime() },
        checkInDays = habit.checkInDays
    )
}