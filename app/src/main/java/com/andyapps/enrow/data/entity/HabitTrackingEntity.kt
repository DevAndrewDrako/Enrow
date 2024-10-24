package com.andyapps.enrow.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "HABIT_TRACKING"
)
data class HabitTrackingEntity(
    @PrimaryKey()
    @ColumnInfo("ID")
    val id: String,

    @ColumnInfo("HABIT_ID")
    val habitId: String,

    @ColumnInfo("HABIT_ACTION")
    val habitAction: Int,

    @ColumnInfo("CREATED_AT")
    val createdAt: Long,
)
