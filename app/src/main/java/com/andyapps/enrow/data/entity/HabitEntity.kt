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

    @ColumnInfo("NAME")
    var name: String,

    @ColumnInfo("SELECTED_DAYS")
    val selectedDays: String,

    @ColumnInfo("CREATED_AT")
    val createdAt: Long,
)
