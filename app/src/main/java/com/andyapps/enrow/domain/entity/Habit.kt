package com.andyapps.enrow.domain.entity

import com.andyapps.enrow.domain.valueobject.SelectedDaysSet
import java.util.Calendar
import java.util.UUID

class Habit private constructor(
    val id: UUID,
    var name: String,
    var selectedDays: SelectedDaysSet,
    val createdAt: Calendar
) {

    fun update(new: Habit) {
        name = new.name
    }

    companion object {
        fun create(name: String) : Habit {
            val current = Calendar.getInstance()

            return Habit(UUID.randomUUID(), name, SelectedDaysSet.allDays(), current)
        }
    }
}