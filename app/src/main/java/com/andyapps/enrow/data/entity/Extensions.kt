package com.andyapps.enrow.data.entity

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.dto.HabitTrackingDto
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.entity.HabitTracking
import com.andyapps.enrow.domain.enumeration.HabitAction
import java.util.UUID

fun Habit.asDbEntity() : HabitEntity {
    return HabitEntity(
        id = id.toString(),
        name = name,
        selectedDays = selectedDays.asString(),
        createdAt = createdAt.timeInMillis
    )
}

fun HabitTracking.asDbEntity() : HabitTrackingEntity {
    return HabitTrackingEntity(
        id = id.toString(),
        habitId = habitId.toString(),
        habitAction = habitAction.ordinal,
        createdAt = createdAt.timeInMillis
    )
}

fun HabitTrackingEntity.asDto() : HabitTrackingDto {
    return HabitTrackingDto(
        id = UUID.fromString(id),
        habitId = UUID.fromString(habitId),
        habitAction = HabitAction.entries.first { it.ordinal == habitAction }
    )
}
fun HabitWithTracking.asDto() : HabitDto {
    return HabitDto(
        id = UUID.fromString(habit.id),
        name = habit.name,
        latestTrackings = trackings
            .filter { it.habitAction == HabitAction.CHECKED.ordinal }
            .sortedByDescending { it.createdAt }
            .take(5)
            .map { it.asDto() },
        daysInRow = trackings.count { it.habitAction == HabitAction.CHECKED.ordinal }
    )
}