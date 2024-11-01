package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.application.service.HabitLogService
import com.andyapps.enrow.domain.entity.HabitLog
import com.andyapps.enrow.domain.enumeration.HabitEventType
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.domain.repository.HabitLogRepository
import com.andyapps.enrow.shared.Res
import com.andyapps.enrow.shared.isNextDayOf
import com.andyapps.enrow.shared.isTodayOf
import java.util.UUID

class CheckHabitUseCase(
    private val repository: HabitLogRepository,
    private val habitRepository: HabitRepository,
    private val service: HabitLogService,
) {
    suspend fun execute(id: UUID) : Res<Unit, UseCaseError.CheckHabit> {
        val habit = habitRepository.get(id)
            ?: return Res.Error(UseCaseError.CheckHabit.NOT_FOUND)

        val log = HabitLog.create(
            habitId = habit.id.value,
            eventType = HabitEventType.CHECKED_IN,
            description = "$habit was checked in."
        )

        val lastCheckInLog = service.getLatestLog(habit.id.value.toString(), HabitEventType.CHECKED_IN.ordinal)

        if (lastCheckInLog == null) {
            if (log.createdAt.isNextDayOf(habit.createdAt.value)) {
                repository.create(log)

                return Res.Success(Unit)
            }
            else {
                return Res.Error(UseCaseError.CheckHabit.JUST_CREATED)
            }
        }
        else {
            if (log.createdAt.isNextDayOf(lastCheckInLog.createdAt)) {
                repository.create(log)

                return Res.Success(Unit)
            }
            else if(!log.createdAt.isTodayOf(lastCheckInLog.createdAt)) {
                return Res.Error(UseCaseError.CheckHabit.EXPIRED)
            }
            else {
                return Res.Error(UseCaseError.CheckHabit.ALREADY_CHECKED)
            }
        }
    }
}