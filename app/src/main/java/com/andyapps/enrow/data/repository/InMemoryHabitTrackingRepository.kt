package com.andyapps.enrow.data.repository

import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.domain.entity.HabitTracking
import com.andyapps.enrow.domain.repository.HabitTrackingRepository

class InMemoryHabitTrackingRepository(
    private val database: InMemoryDatabase
) : HabitTrackingRepository {
    override suspend fun create(tracking: HabitTracking) {
        database.habitTrackings.add(tracking)
    }

    override suspend fun delete(tracking: HabitTracking) {
        database.habitTrackings.removeIf { it.id == tracking.id }
    }
}