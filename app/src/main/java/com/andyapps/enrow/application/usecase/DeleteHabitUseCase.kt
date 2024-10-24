package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.shared.Res
import java.util.UUID

class DeleteHabitUseCase(
    private val repository: HabitRepository
) {
    suspend fun execute(id: UUID) : Res<Unit, UseCaseError.DeleteHabit> {
        return repository.get(id)?.let {
            repository.delete(it)

            Res.Success(Unit)
        } ?: Res.Error(UseCaseError.DeleteHabit.NOT_FOUND)
    }
}