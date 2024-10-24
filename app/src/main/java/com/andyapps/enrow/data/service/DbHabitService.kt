package com.andyapps.enrow.data.service

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.data.dao.HabitDao
import com.andyapps.enrow.data.entity.asDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class DbHabitService(
    private val dao: HabitDao
) : HabitService {
    override fun getAll(): Flow<List<HabitDto>> {
        return dao.getAllWithTrackings().map {
            list -> list.map {
                it.asDto()
            }
        }
    }

    override suspend fun existsById(id: UUID): Boolean {
        var exists = false

        dao.getAll().collect { habits ->
            if (habits.any { it.id == id.toString() }) {
                exists = true

                return@collect
            }
        }

        return exists
    }

    override suspend fun existsByName(name: String): Boolean {
        var exists = false

        dao.getAll().collect { habits ->
            if (habits.any { it.name.uppercase() == name.uppercase() }) {
                exists = true

                return@collect
            }
        }

        return exists
    }

    override suspend fun getByIdWithTracking(id: UUID): HabitDto? {
        return dao.getByIdWithTracking(id.toString())?.asDto()
    }
}