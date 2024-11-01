package com.andyapps.enrow.domain.valueobject

import com.andyapps.enrow.domain.error.HabitErrorCode
import com.andyapps.enrow.domain.error.HabitException

class SelectedDaysSet(
    value: String
) {
    private val map: MutableMap<Int, Boolean>  = mutableMapOf()

    init {
        if (value.length != 7) {
            throw HabitException(HabitErrorCode.INCORRECT_SELECTED_DAYS)
        }

        var dayIndex = 1
        for (c in value) {
            map[dayIndex++] = c == '1'
        }
    }

    fun isSelected(day: Int) : Boolean {
        return map[day]!!
    }

    fun asString(): String {
        return map.map { if (it.value) '1' else '0' }.joinToString("")
    }

    companion object {
        const val ALL_DAYS = "1111111"
        const val ZERO_DAYS = "0000000"
        const val ONLY_WORKING_DAYS = "1111100"

        fun allDays() : SelectedDaysSet {
            return SelectedDaysSet(ALL_DAYS)
        }
    }
}