package com.andyapps.enrow.presentation.model

import com.andyapps.enrow.domain.enumeration.Day

data class UiDay(
    val value: Day
) {
    fun asString() : String {
        return value.toString()
    }
}
