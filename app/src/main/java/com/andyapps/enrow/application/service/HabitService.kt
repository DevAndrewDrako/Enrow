package com.andyapps.enrow.application.service

import com.andyapps.enrow.application.dto.HabitDto
import kotlinx.coroutines.flow.Flow

interface HabitService {
    fun getAll() : Flow<List<HabitDto>>
}