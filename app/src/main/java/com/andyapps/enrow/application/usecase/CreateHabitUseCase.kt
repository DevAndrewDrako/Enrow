package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.shared.Res
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.entity.HabitLog
import com.andyapps.enrow.domain.enumeration.HabitEventType
import com.andyapps.enrow.domain.repository.HabitLogRepository
import com.andyapps.enrow.domain.repository.HabitRepository

class CreateHabitUseCase(
    private val repository: HabitRepository,
    private val logRepository: HabitLogRepository,
    private val service: HabitService
) {
    suspend fun execute(habit: Habit) : Res<Unit, UseCaseError.CreateHabit> {
        if (service.existsById(habit.id.value))
            return Res.Error(UseCaseError.CreateHabit.ALREADY_EXISTS_BY_ID)

        if (service.existsByName(habit.name.value))
            return Res.Error(UseCaseError.CreateHabit.ALREADY_EXISTS_BY_NAME)

        repository.create(habit)

        logRepository.create(HabitLog.create(
            habitId = habit.id.value,
            eventType = HabitEventType.CREATED,
            description = "$habit was created."
        ))

        return Res.Success(Unit)
    }
}