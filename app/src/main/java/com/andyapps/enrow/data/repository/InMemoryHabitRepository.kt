package com.andyapps.enrow.data.repository

import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.repository.HabitRepository
import java.util.UUID

class InMemoryHabitRepository(
    private val database: InMemoryDatabase
) : HabitRepository {
    override suspend fun create(habit: Habit) {
        database.habits.add(habit)
    }

    override suspend fun update(habit: Habit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(habit: Habit) {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: UUID) : Habit? {
        return database.habits.firstOrNull { it.id == id }
    }
}