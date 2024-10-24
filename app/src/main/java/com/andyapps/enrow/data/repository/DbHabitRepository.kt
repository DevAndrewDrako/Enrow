package com.andyapps.enrow.data.repository

import com.andyapps.enrow.data.dao.HabitDao
import com.andyapps.enrow.data.entity.asDbEntity
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.repository.HabitRepository
import java.util.UUID

class DbHabitRepository(
    private val dao: HabitDao
) : HabitRepository {
    override suspend fun create(habit: Habit) {
        dao.insert(habit.asDbEntity())
    }

    override suspend fun update(habit: Habit) {
        dao.update(habit.asDbEntity())
    }

    override suspend fun delete(habit: Habit) {
        dao.delete(habit.asDbEntity())
    }

    override suspend fun get(id: UUID) : Habit? {
        val dbEntity = dao.getById(id.toString()) ?: return null

        return null
    }
}