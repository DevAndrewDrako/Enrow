package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.shared.Res
import java.util.UUID

class UpdateHabitUseCase(
    private val repository: HabitRepository,
) {
    suspend fun execute(id: UUID, habit: Habit) : Res<Habit, UseCaseError.UpdateHabit> {
        val alreadyExists = repository.get(id) ?: return Res.Error(UseCaseError.UpdateHabit.NOT_FOUND)

        alreadyExists.update(habit)
        repository.update(habit)

        return Res.Success(alreadyExists)
    }
}