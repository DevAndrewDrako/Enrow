package com.andyapps.enrow.application.dto

import com.andyapps.enrow.domain.valueobject.CheckInDaysSet
import java.util.Calendar
import java.util.UUID

data class HabitDto(
    val id: UUID,
    val name: String,
    private val checkIns: List<Calendar>,
    private val checkInDays: String,
) {
    fun daysInRow() : Int {
        return checkIns.count()
    }

    fun latestCheckIns() : List<Calendar> {
        return checkIns.sortedByDescending { it.timeInMillis }.take(5)
    }

    fun selectedDays() : Map<Int, Boolean> {
        val map = checkInDays.mapIndexed { index, c ->  Pair(index, c == CheckInDaysSet.DAY_IS_SELECTED)}.toMap()

        return map
    }
}
