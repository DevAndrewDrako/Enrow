package com.andyapps.enrow.domain.repository

import com.andyapps.enrow.domain.entity.HabitTracking

interface HabitTrackingRepository {
    suspend fun create(tracking: HabitTracking)
    suspend fun delete(tracking: HabitTracking)
}