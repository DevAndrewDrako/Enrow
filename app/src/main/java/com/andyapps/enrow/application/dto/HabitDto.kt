package com.andyapps.enrow.application.dto

import java.util.Calendar
import java.util.UUID

data class HabitDto(
    val id: UUID,
    val name: String,
    private val checkIns: List<Calendar>,
    private val selectedDays: String,
) {
    fun daysInRow() : Int {
        return checkIns.count()
    }

    fun latestCheckIns() : List<Calendar> {
        return checkIns.sortedByDescending { it.timeInMillis }.take(5)
    }

    fun selectedDays() : Map<Int, Boolean> {
        val map = selectedDays.mapIndexed { index, c ->  Pair(index + 1, c == '1')}.toMap()

        return map
    }
}
