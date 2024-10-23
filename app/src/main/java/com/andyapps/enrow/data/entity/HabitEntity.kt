package com.andyapps.enrow.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "HABIT"
)
data class HabitEntity(
    @PrimaryKey()
    @ColumnInfo("ID")
    val id: String,

    @ColumnInfo("STARTED_AT")
    val startedAt: Long,

    @ColumnInfo("CHECKED_AT")
    val checkedAt: Long
)
