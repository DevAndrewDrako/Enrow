package com.andyapps.enrow.domain.entity

import com.andyapps.enrow.domain.error.HabitErrorCode
import com.andyapps.enrow.domain.error.HabitException
import java.util.Calendar
import java.util.UUID

class Habit private constructor(
    val id: UUID,
    private val startedAt: Calendar,
    private var checkedAt: Calendar
) {

    fun check() {
        val daysDiff = checkedAt[Calendar.DAY_OF_YEAR] - startedAt[Calendar.DAY_OF_YEAR]

        if (daysDiff != 1) {
            throw HabitException(HabitErrorCode.UNABLE_TO_CHECK)
        }

        checkedAt = Calendar.getInstance()
    }

    fun daysInRow() : Int {
        val diff = checkedAt[Calendar.DAY_OF_YEAR] - startedAt[Calendar.DAY_OF_YEAR]

        return if (diff < 0) 0 else diff
    }

    companion object {
        fun create() : Habit {
            val current = Calendar.getInstance()

            return Habit(UUID.randomUUID(), current, current)
        }
    }
}