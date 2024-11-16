package com.andyapps.enrow.data.repository

import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.repository.HabitRepository
import java.util.UUID

class TestHabitRepository(
    private val habits: MutableList<Habit>
) : HabitRepository {

    override suspend fun create(habit: Habit) {
        habits.add(habit)
    }

    override suspend fun update(habit: Habit) {
        habits.forEach {
            if (it.id == habit.id) {
                it.update(habit)
            }
        }
    }

    override suspend fun delete(habit: Habit) {
        val index = habits.indexOfFirst { it.id == habit.id }
        habits.removeAt(index)
    }

    override suspend fun get(id: UUID): Habit? {
        return habits.firstOrNull { it.id == id }
    }

}