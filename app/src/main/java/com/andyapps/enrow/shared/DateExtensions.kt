package com.andyapps.enrow.shared

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
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

fun LocalDateTime.toFormat(pattern: String = "dd/MM/yyyy HH:mm:ss") : String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}

fun LocalDateTime.isSameDay(other: LocalDateTime): Boolean {
    return this.year == other.year && this.dayOfYear == other.dayOfYear
}

fun Long.toLocalDateTime() : LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneOffset.UTC)
}

fun LocalDateTime.toMilliseconds() : Long {
    return ZonedDateTime.of(this, ZoneId.systemDefault()).toInstant().toEpochMilli()
}