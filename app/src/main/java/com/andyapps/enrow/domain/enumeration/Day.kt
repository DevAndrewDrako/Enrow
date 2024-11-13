package com.andyapps.enrow.domain.enumeration

import java.util.Calendar

enum class Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    companion object {
        fun fromCalendar(calendar: Calendar) : Day? {
            val dayOfAWeek = calendar[Calendar.DAY_OF_WEEK]

            return when (dayOfAWeek) {
                Calendar.MONDAY -> Day.MONDAY
                Calendar.TUESDAY -> Day.TUESDAY
                Calendar.WEDNESDAY -> Day.WEDNESDAY
                Calendar.THURSDAY -> Day.THURSDAY
                Calendar.FRIDAY -> Day.FRIDAY
                Calendar.SATURDAY -> Day.SATURDAY
                Calendar.SUNDAY -> Day.SUNDAY
                else -> null
            }
        }
    }
}