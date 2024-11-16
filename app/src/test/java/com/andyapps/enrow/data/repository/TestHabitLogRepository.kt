package com.andyapps.enrow.data.repository

import com.andyapps.enrow.domain.entity.HabitLog
import com.andyapps.enrow.domain.repository.HabitLogRepository

class TestHabitLogRepository : HabitLogRepository {
    private val logs = mutableListOf<HabitLog>()

    override suspend fun create(log: HabitLog) {
        logs.add(log)
    }
}