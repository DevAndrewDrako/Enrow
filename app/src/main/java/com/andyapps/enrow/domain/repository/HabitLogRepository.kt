package com.andyapps.enrow.domain.repository

import com.andyapps.enrow.domain.entity.HabitLog

interface HabitLogRepository {
    suspend fun create(log: HabitLog)
}