package com.andyapps.enrow.application.service

import com.andyapps.enrow.application.dto.HabitLogDto
import kotlinx.coroutines.flow.Flow

interface HabitLogService {
    fun getAll() : Flow<List<HabitLogDto>>
    suspend fun getLatestLog(habitId: String, eventType: Int) : HabitLogDto?
}