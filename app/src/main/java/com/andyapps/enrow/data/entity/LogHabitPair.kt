package com.andyapps.enrow.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class LogHabitPair(
    @Relation(
        parentColumn = "HABIT_ID",
        entityColumn = "ID"
    )
    val habit: HabitEntity,
    @Embedded
    val log: HabitLogEntity
)
