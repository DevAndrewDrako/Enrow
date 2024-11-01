package com.andyapps.enrow.data.service

import com.andyapps.enrow.application.dto.HabitLogDto
import com.andyapps.enrow.application.dto.asDto
import com.andyapps.enrow.application.service.HabitLogService
import com.andyapps.enrow.data.dao.HabitLogDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DbHabitLogService(
    private val dao: HabitLogDao
) : HabitLogService {
    override fun getAll(): Flow<List<HabitLogDto>> {
        return dao.getAll().map { list -> list.map { it.log.asDto(it.habit.name) } }
    }

    override suspend fun getLatestLog(habitId: String, eventType: Int): HabitLogDto? {
        return dao.getLatestLog(habitId, eventType)?.let {
            it.log.asDto(it.habit.name)
        }
    }
}