package com.andyapps.enrow.presentation.ui.shared

import com.andyapps.enrow.domain.enumeration.Day
import com.andyapps.enrow.presentation.model.UiDay

object Filler {
    fun fillSelectedDays() : Set<SelectItem<UiDay>> {
        return Day.entries.map { day ->
            SelectItem(UiDay(day), false)
        }.toSet()
    }
}