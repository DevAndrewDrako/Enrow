package com.andyapps.enrow.application.dto

import com.andyapps.enrow.domain.entity.Habit

fun Habit.asDto() : HabitDto {
    return HabitDto(
        id = id,
        name = name,
        daysInRow = 0
    )
}