package com.andyapps.enrow.shared

import java.util.Calendar
import java.util.Locale

fun Calendar.toFormat() : String {
    val year = this[Calendar.YEAR]
    val month = this[Calendar.MONTH] + 1
    val day = this[Calendar.DAY_OF_MONTH]

    val hour = this[Calendar.HOUR_OF_DAY]
    val minute = this[Calendar.MINUTE]
    val second = this[Calendar.SECOND]

    val date = String.format(Locale.ENGLISH, "%02d/%02d/%d", day, month, year)
    val time = String.format(Locale.ENGLISH, "%02d:%02d:%02d", hour, minute, second)
    return "$date $time"
}

fun Calendar.isNextDayOf(date: Calendar) : Boolean {
    val nextDay = (date.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, 1) }

    return this[Calendar.YEAR] == nextDay[Calendar.YEAR]
            && this[Calendar.DAY_OF_YEAR] == nextDay[Calendar.DAY_OF_YEAR]
}

fun Calendar.isTodayOf(date: Calendar) : Boolean {
    return this[Calendar.YEAR] == date[Calendar.YEAR]
            && this[Calendar.DAY_OF_YEAR] == date[Calendar.DAY_OF_YEAR]
}