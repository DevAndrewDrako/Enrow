package com.andyapps.enrow.data.dao

import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.data.entity.HabitTrackingEntity

class InMemoryHabitTrackingDao(
    private val db: InMemoryDatabase
) : HabitTrackingDao {
    override suspend fun insert(tracking: HabitTrackingEntity) {
        db.habitTrackings.add(tracking)
    }

    override suspend fun delete(tracking: HabitTrackingEntity) {
        db.habitTrackings.removeIf { it.id == tracking.id }
    }
}