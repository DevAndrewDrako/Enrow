package com.andyapps.enrow.presentation.ui.shared

import com.andyapps.enrow.domain.enumeration.Day
import com.andyapps.enrow.presentation.model.UiDay

object UiTextHelper {
    fun displayCheckInDays(days: Set<UiDay>) : String {
        if (days.isEmpty()) {
            return "not selected"
        }

        val values = days.map { it.value }

        if (values.containsAll(Day.entries)) {
            return "all days"
        }
        else if (values.containsAll(listOf(
            Day.MONDAY,
            Day.TUESDAY,
            Day.WEDNESDAY,
            Day.THURSDAY,
            Day.FRIDAY,
        ))) {
            return "working days only"
        }
        else if (values.containsAll(listOf(
                Day.SATURDAY,
                Day.SUNDAY,
            ))) {
            return "weekends only"
        }
        else {
            return days.joinToString(", ") { it.asString() }
        }
    }
}