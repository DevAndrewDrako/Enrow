package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.error.HabitErrorCode
import com.andyapps.enrow.domain.error.HabitException
import com.andyapps.enrow.shared.Res

class ValidateHabitUseCase {
    fun execute(name: String, checkInDays: String, timeInMillis: Long) : Res<Habit, HabitErrorCode> {
        return try {
            Res.Success(Habit.create(name, checkInDays, timeInMillis))
        } catch (ex: HabitException) {
            Res.Error(ex.errorCode)
        }
    }
}