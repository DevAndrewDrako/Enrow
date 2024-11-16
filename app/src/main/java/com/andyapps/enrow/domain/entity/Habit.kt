package com.andyapps.enrow.domain.entity

import com.andyapps.enrow.domain.error.HabitErrorCode
import com.andyapps.enrow.domain.error.HabitException
import com.andyapps.enrow.domain.valueobject.CheckInDaysSet
import com.andyapps.enrow.shared.toLocalDateTime
import com.andyapps.enrow.shared.toUUID
import java.time.LocalDateTime
import java.util.Calendar
import java.util.UUID

class Habit private constructor(
    val id: UUID,
    var name: String,
    var checkInDays: CheckInDaysSet,
    val createdAt: LocalDateTime
) {
    fun update(new: Habit) {
        name = new.name
        checkInDays = new.checkInDays
    }

    override fun toString(): String {
        return "Habit [id=\"${id}\", name=\"${name}\", selectedDays=\"$checkInDays\"]"
    }

    companion object {
        private const val HABIT_NAME_MAX_LENGTH = 20

        fun create(name: String, checkInDays: String, timeInMillis: Long) : Habit {
            return Habit(
                id = UUID.randomUUID(),
                name = validateName(name),
                checkInDays = validateCheckInDays(checkInDays),
                createdAt = timeInMillis.toLocalDateTime()
            )
        }

        fun create(id: String, name: String, selectedDays: String, createdAt: Long) : Habit {
            return Habit(
                id = UUID.fromString(id),
                name = validateName(name),
                checkInDays = validateCheckInDays(selectedDays),
                createdAt = createdAt.toLocalDateTime()
            )
        }

        private fun validateName(name: String) : String {
            if (name.isEmpty() || name.isBlank()) {
                throw HabitException(HabitErrorCode.NAME_IS_BLANK_OR_EMPTY)
            }

            if (name.length > HABIT_NAME_MAX_LENGTH) {
                throw HabitException(HabitErrorCode.NAME_IS_TOO_LONG)
            }

            return name
        }

        private fun validateCheckInDays(checkInDays: String) : CheckInDaysSet {
            return CheckInDaysSet(checkInDays)
        }
    }
}