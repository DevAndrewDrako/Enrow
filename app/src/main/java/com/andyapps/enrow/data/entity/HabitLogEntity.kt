package com.andyapps.enrow.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "HABIT_LOG"
)
data class HabitLogEntity(
    @PrimaryKey()
    @ColumnInfo("ID")
    val id: String,

    @ColumnInfo("HABIT_ID")
    val habitId: String,

    @ColumnInfo("EVENT_TYPE")
    val eventType: Int,

    @ColumnInfo("DESCRIPTION")
    val description: String,

    @ColumnInfo("CREATED_AT")
    val createdAt: Long,
)
