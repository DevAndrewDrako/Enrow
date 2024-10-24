package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.domain.repository.HabitTrackingRepository

class CheckHabitUseCase(
    private val repository: HabitTrackingRepository
) {
    suspend fun execute() {

    }
}