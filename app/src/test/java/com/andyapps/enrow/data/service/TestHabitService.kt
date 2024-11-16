package com.andyapps.enrow.data.service

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.domain.entity.Habit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class TestHabitService(
    private val habits: List<Habit>
) : HabitService {

    private fun list() : List<HabitDto> {
        return habits.map {
            HabitDto(
                id = it.id,
                name = it.name,
                checkIns = emptyList(),
                checkInDays = it.checkInDays.toString()
            )
        }
    }

    override fun getAll(): Flow<List<HabitDto>> = flow {
        emit(list())
    }

    override suspend fun existsById(id: UUID): Boolean {
        return list().any { it.id == id }
    }

    override suspend fun existsByName(name: String): Boolean {
        return list().any { it.name == name }
    }

    override suspend fun getById(id: UUID): HabitDto? {
        return list().firstOrNull { it.id == id }
    }

}