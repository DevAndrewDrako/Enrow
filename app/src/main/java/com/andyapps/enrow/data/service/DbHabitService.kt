package com.andyapps.enrow.data.service

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.dto.asDto
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.data.dao.HabitDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.UUID

class DbHabitService(
    private val dao: HabitDao
) : HabitService {
    override fun getAll(): Flow<List<HabitDto>> {
        return dao.getAll().map { list ->
            list.map { it.asDto() }
        }
    }

    override suspend fun existsById(id: UUID): Boolean {
        val habitWithLogs = dao.getAll().firstOrNull() ?: emptyList()
        return habitWithLogs.any { it.habit.id == id.toString() }
    }

    override suspend fun existsByName(name: String): Boolean {
        val habitWithLogs = dao.getAll().firstOrNull() ?: emptyList()
        return habitWithLogs.any { it.habit.name.uppercase() == name.uppercase() }
    }

    override suspend fun getById(id: UUID): HabitDto? {
        return dao.getByIdWithLogs(id.toString())?.asDto()
    }
}