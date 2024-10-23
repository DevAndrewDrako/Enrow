package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.service.HabitService
import kotlinx.coroutines.flow.Flow

class GetAllHabitsUseCase(
    private val service: HabitService
) {
    fun execute() : Flow<List<HabitDto>> {
        return service.getAll()
    }
}