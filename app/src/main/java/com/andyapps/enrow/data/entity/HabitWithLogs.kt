package com.andyapps.enrow.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class HabitWithLogs(
    @Embedded
    val habit: HabitEntity,
    @Relation(
        parentColumn = "ID",
        entityColumn = "HABIT_ID"
    )
    val logs: List<HabitLogEntity>
)
