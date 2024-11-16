package com.andyapps.enrow.domain.valueobject

import com.andyapps.enrow.domain.error.HabitErrorCode
import com.andyapps.enrow.domain.error.HabitException
import java.time.DayOfWeek

class CheckInDaysSet(
    value: String
) {
    private val map: MutableMap<DayOfWeek, Boolean> = mutableMapOf()

    init {
        if (value.length != 7) {
            throw HabitException(HabitErrorCode.INCORRECT_SELECTED_DAYS)
        }

        DayOfWeek.entries.forEachIndexed { index, day ->
            val char = value.getOrElse(index) { DAY_IS_NOT_SELECTED }

            map[day] = char == DAY_IS_SELECTED
        }
    }

    fun isSelected(day: DayOfWeek) : Boolean {
        return map.getOrDefault(day, false)
    }

    override fun toString(): String {
        return map.map { if (it.value) DAY_IS_SELECTED else DAY_IS_NOT_SELECTED }.joinToString("")
    }

    companion object {
        const val ALL_DAYS = "1111111"
        const val DAY_IS_SELECTED = '1'
        const val DAY_IS_NOT_SELECTED = '0'

        fun allDays() : CheckInDaysSet {
            return CheckInDaysSet(ALL_DAYS)
        }
    }
}