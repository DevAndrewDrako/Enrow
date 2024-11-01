package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.domain.entity.HabitLog
import com.andyapps.enrow.domain.enumeration.HabitEventType
import com.andyapps.enrow.domain.repository.HabitLogRepository
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.shared.Res
import java.util.UUID

class DeleteHabitUseCase(
    private val repository: HabitRepository,
    private val logRepository: HabitLogRepository
) {
    suspend fun execute(id: UUID) : Res<Unit, UseCaseError.DeleteHabit> {
        return repository.get(id)?.let { habit ->
            repository.delete(habit)

            logRepository.create(
                HabitLog.create(
                    habitId = habit.id.value,
                    eventType = HabitEventType.DELETED,
                    description = "$habit was deleted."
                )
            )

            Res.Success(Unit)
        } ?: Res.Error(UseCaseError.DeleteHabit.NOT_FOUND)
    }
}