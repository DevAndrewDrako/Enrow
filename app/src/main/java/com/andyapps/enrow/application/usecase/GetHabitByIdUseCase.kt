package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.service.HabitService
import java.util.UUID

class GetHabitByIdUseCase(
    private val service: HabitService
) {
    suspend fun execute(id: UUID) : HabitDto? {
        return service.getByIdWithTracking(id)
    }
}