package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.shared.Res
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.repository.HabitRepository

class CreateHabitUseCase(
    private val repository: HabitRepository,
    private val service: HabitService
) {
    suspend fun execute(habit: Habit) : Res<Unit, UseCaseError.CreateHabit> {
        if (service.existsById(habit.id))
            return Res.Error(UseCaseError.CreateHabit.ALREADY_EXISTS_BY_ID)

        if (service.existsByName(habit.name))
            return Res.Error(UseCaseError.CreateHabit.ALREADY_EXISTS_BY_NAME)

        repository.create(habit)

        return Res.Success(Unit)
    }
}