package com.andyapps.enrow.application.dto

import java.util.Calendar
import java.util.UUID

data class HabitDto(
    val id: UUID,
    val name: String,
    val checkIns: List<Calendar>,
) {
    fun daysInRow() : Int {
        return checkIns.count()
    }

    fun latestCheckIns() : List<Calendar> {
        return checkIns.sortedByDescending { it.timeInMillis }.take(5)
    }
}
