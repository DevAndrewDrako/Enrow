package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.dto.HabitLogDto
import com.andyapps.enrow.application.service.HabitLogService
import kotlinx.coroutines.flow.Flow

class GetHabitLogsUseCase(
    private val service: HabitLogService
) {
    fun execute() : Flow<List<HabitLogDto>> {
        return service.getAll()
    }
}