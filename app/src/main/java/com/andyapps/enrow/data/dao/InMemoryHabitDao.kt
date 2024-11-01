package com.andyapps.enrow.data.dao

import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitWithLogs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InMemoryHabitDao(
    private val db: InMemoryDatabase
) : HabitDao {
    override suspend fun insert(habit: HabitEntity) {
        db.habits.add(habit)
    }

    override suspend fun update(habit: HabitEntity) {
        db.habits.find { it.id == habit.id }?.apply {
            name = habit.name
            isDeleted = habit.isDeleted
        }
    }

    override fun getAll(): Flow<List<HabitWithLogs>> {
        return flow {
            emit(
                db.habits.filter { !it.isDeleted }.map { habit ->
                    val logs = db.habitLogs.filter { it.habitId == habit.id }

                    HabitWithLogs(
                        habit = habit,
                        logs = logs
                    )
                }
            )
        }
    }

    override suspend fun getById(id: String): HabitEntity? {
        return db.habits.firstOrNull { it.id == id }
    }

    override suspend fun getByIdWithLogs(id: String): HabitWithLogs? {
        val habit = db.habits.firstOrNull { it.id == id } ?: return null

        return HabitWithLogs(
            habit = habit,
            logs = db.habitLogs.filter { it.habitId == habit.id }
        )
    }
}