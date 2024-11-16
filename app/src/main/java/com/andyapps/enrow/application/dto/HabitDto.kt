package com.andyapps.enrow.application.dto

import com.andyapps.enrow.domain.valueobject.CheckInDaysSet
import com.andyapps.enrow.shared.isSameDay
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.UUID

data class HabitDto(
    val id: UUID,
    val name: String,
    private val checkIns: List<LocalDateTime>,
    private val checkInDays: String,
) {
    fun daysInRow() : Int {
        return checkIns.count()
    }

    fun latestCheckIns() : List<LocalDateTime> {
        return checkIns.sortedByDescending { it }.take(5)
    }

    fun isCheckInDay(dayOfWeek: DayOfWeek) : Boolean {
        return checkInDays.getOrNull(dayOfWeek.value - 1) == CheckInDaysSet.DAY_IS_SELECTED
    }

    fun canCheckIn() : Boolean {
        val now = LocalDateTime.now()

        if (!isCheckInDay(now.dayOfWeek)) return false

        val last = latestCheckIns().firstOrNull() ?: return true

        return !LocalDateTime.now().isSameDay(last)
    }
}
