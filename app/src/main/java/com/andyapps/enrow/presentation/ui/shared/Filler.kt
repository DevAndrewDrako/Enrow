package com.andyapps.enrow.presentation.ui.shared

import com.andyapps.enrow.presentation.model.UiDay
import java.time.DayOfWeek

object Filler {
    fun fillSelectedDays() : Set<SelectItem<UiDay>> {
        return DayOfWeek.entries.map { day ->
            SelectItem(UiDay(day), false)
        }.toSet()
    }
}