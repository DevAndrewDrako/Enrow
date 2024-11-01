package com.andyapps.enrow.application.service

import com.andyapps.enrow.application.dto.HabitDto
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface HabitService {
    fun getAll() : Flow<List<HabitDto>>
    suspend fun existsById(id: UUID) : Boolean
    suspend fun existsByName(name: String) : Boolean
    suspend fun getById(id: UUID) : HabitDto?
}