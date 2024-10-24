package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.repository.HabitRepository
import java.util.UUID

class GetHabitByIdUseCase(
    private val repository: HabitRepository
) {
    suspend fun execute(id: UUID) : Habit? {
        return repository.get(id)
    }
}