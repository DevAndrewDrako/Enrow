package com.andyapps.enrow.presentation.ui.shared

import com.andyapps.enrow.presentation.model.UiDay
import java.time.DayOfWeek

object UiTextHelper {
    fun displayCheckInDays(days: Set<UiDay>) : String {
        if (days.isEmpty()) {
            return "not selected"
        }

        val values = days.map { it.value }

        if (values.containsAll(DayOfWeek.entries)) {
            return "all days"
        }
        else if (values.all { value ->
            listOf(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
            ).contains(value)
        }) {
            return "working days only"
        }
        else if (values.all { value ->
                listOf(
                    DayOfWeek.SUNDAY,
                    DayOfWeek.SATURDAY
                ).contains(value)
            }) {
            return "weekends only"
        }
        else {
            return days.joinToString(", ") { it.asString() }
        }
    }
}