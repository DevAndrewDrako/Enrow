package com.andyapps.enrow.data.entity

import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.entity.HabitLog
import com.andyapps.enrow.shared.toMilliseconds

fun Habit.asDbEntity() : HabitEntity {
    return HabitEntity(
        id = id.toString(),
        name = name,
        checkInDays = checkInDays.toString(),
        createdAt = createdAt.toMilliseconds(),
        isDeleted = false
    )
}

fun HabitLog.asDbEntity() : HabitLogEntity {
    return HabitLogEntity(
        id = id.toString(),
        habitId = habitId.toString(),
        eventType = eventType.ordinal,
        createdAt = createdAt.toMilliseconds(),
        description = description
    )
}

fun HabitEntity.asDomainOrNull() : Habit? {
    return try {
        Habit.create(id, name, checkInDays, createdAt)
    }
    catch (ex: Exception) {
        null
    }
}