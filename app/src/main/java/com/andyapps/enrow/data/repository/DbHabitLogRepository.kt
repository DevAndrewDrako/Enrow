package com.andyapps.enrow.data.repository

import com.andyapps.enrow.data.dao.HabitLogDao
import com.andyapps.enrow.data.entity.asDbEntity
import com.andyapps.enrow.domain.entity.HabitLog
import com.andyapps.enrow.domain.repository.HabitLogRepository

class DbHabitLogRepository(
    private val dao: HabitLogDao
) : HabitLogRepository {
    override suspend fun create(log: HabitLog) {
        dao.insert(log.asDbEntity())
    }
}