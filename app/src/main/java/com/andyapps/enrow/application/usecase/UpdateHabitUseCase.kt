package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.entity.HabitLog
import com.andyapps.enrow.domain.enumeration.HabitEventType
import com.andyapps.enrow.domain.repository.HabitLogRepository
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.shared.Res
import java.util.UUID

class UpdateHabitUseCase(
    private val repository: HabitRepository,
    private val logRepository: HabitLogRepository
) {
    suspend fun execute(id: UUID, habit: Habit) : Res<Habit, UseCaseError.UpdateHabit> {
        val alreadyExists = repository.get(id) ?: return Res.Error(UseCaseError.UpdateHabit.NOT_FOUND)
        val oldHabitToString = alreadyExists.toString()

        alreadyExists.update(habit)
        repository.update(alreadyExists)

        logRepository.create(
            HabitLog.create(
                habitId = alreadyExists.id,
                eventType = HabitEventType.UPDATED,
                description = "$oldHabitToString was updated to new $alreadyExists"
            )
        )

        return Res.Success(alreadyExists)
    }
}