package com.andyapps.enrow.domain.entity

import java.util.Calendar

class Habit private constructor(
    val startedAt: Calendar,
    var checkedAt: Calendar
) {
    fun check() {

    }

    fun daysInRow() : Int {
        return 0
    }
}