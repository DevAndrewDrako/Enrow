package com.andyapps.enrow.data.service

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.dto.asDto
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.data.dao.HabitDao
import kotlinx.coroutines.flow.Flow
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
        var exists = false

        dao.getAll().collect { habitWithLogs ->
            if (habitWithLogs.any { it.habit.id == id.toString() }) {
                exists = true

                return@collect
            }
        }

        return exists
    }

    override suspend fun existsByName(name: String): Boolean {
        var exists = false

        dao.getAll().collect { habitWithLogs ->
            if (habitWithLogs.any { it.habit.name.uppercase() == name.uppercase() }) {
                exists = true

                return@collect
            }
        }

        return exists
    }

    override suspend fun getById(id: UUID): HabitDto? {
        return dao.getByIdWithLogs(id.toString())?.asDto()
    }
}