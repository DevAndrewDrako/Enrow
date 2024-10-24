package com.andyapps.enrow.data.service

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.dto.asDto
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.data.InMemoryDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class InMemoryHabitService(
    private val database: InMemoryDatabase
) : HabitService {
    override fun getAll(): Flow<List<HabitDto>> {
        return flow {
            emit(database.habits.map { it.asDto() })
        }
    }

    override suspend fun existsById(id: UUID): Boolean {
        return database.habits.any { it.id == id }
    }

    override suspend fun existsByName(name: String): Boolean {
        return database.habits.any { it.name.uppercase() == name.uppercase() }
    }
}