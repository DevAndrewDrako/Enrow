package com.andyapps.enrow.domain.entity

import com.andyapps.enrow.domain.error.HabitErrorCode
import com.andyapps.enrow.domain.error.HabitException
import com.andyapps.enrow.domain.valueobject.SelectedDaysSet
import com.andyapps.enrow.shared.toUUID
import java.util.Calendar
import java.util.UUID

class Habit private constructor(
    val id: Id,
    var name: Name,
    var selectedDays: SelectedDaysSet,
    val createdAt: DateTime
) {

    class Id(
        value: String
    ) {
        val value: UUID = value.toUUID() ?: throw HabitException(HabitErrorCode.INCORRECT_ID)
    }

    class Name(
        val value: String
    ) {
        init {
            if (value.isEmpty() || value.isBlank() || value.length > 24) {
                throw HabitException(HabitErrorCode.INCORRECT_NAME)
            }
        }
    }

    class DateTime(
        value: Long
    ) {
        val value: Calendar = Calendar.getInstance()

        init {
            this.value.timeInMillis = value
        }
    }

    fun update(new: Habit) {
        name = new.name
    }

    override fun toString(): String {
        return "Habit [id=\"${id.value}\", name=\"${name.value}\", selectedDays=\"${selectedDays.asString()}\"]"
    }

    companion object {
        fun create(name: String, selectedDays: SelectedDaysSet = SelectedDaysSet.allDays()) : Habit {
            val current = Calendar.getInstance()

            return Habit(
                id = Id(UUID.randomUUID().toString()),
                name = Name(name),
                selectedDays = SelectedDaysSet(selectedDays.asString()),
                createdAt = DateTime(current.timeInMillis)
            )
        }

        fun create(id: String, name: String, selectedDays: String, createdAt: Long) : Habit{
            return Habit(
                id = Id(id),
                name = Name(name),
                selectedDays = SelectedDaysSet(selectedDays),
                createdAt = DateTime(createdAt)
            )
        }
    }
}