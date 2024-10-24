package com.andyapps.enrow.domain.repository

import com.andyapps.enrow.domain.entity.Habit
import java.util.UUID

interface HabitRepository {
    suspend fun create(habit: Habit)
    suspend fun update(habit: Habit)
    suspend fun delete(habit: Habit)
    suspend fun get(id: UUID) : Habit?
}