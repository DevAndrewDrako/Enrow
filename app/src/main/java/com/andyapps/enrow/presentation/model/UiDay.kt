package com.andyapps.enrow.presentation.model

import java.time.DayOfWeek

data class UiDay(
    val value: DayOfWeek
) {
    fun asString() : String {
        return value.toString()
    }
}
