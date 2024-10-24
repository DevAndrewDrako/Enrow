package com.andyapps.enrow.data.repository

import com.andyapps.enrow.data.dao.HabitTrackingDao
import com.andyapps.enrow.data.entity.asDbEntity
import com.andyapps.enrow.domain.entity.HabitTracking
import com.andyapps.enrow.domain.repository.HabitTrackingRepository

class DbHabitTrackingRepository(
    private val dao: HabitTrackingDao
) : HabitTrackingRepository {
    override suspend fun create(tracking: HabitTracking) {
        dao.insert(tracking.asDbEntity())
    }

    override suspend fun delete(tracking: HabitTracking) {
        dao.delete(tracking.asDbEntity())
    }
}