package com.andyapps.enrow.data.dao

import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitWithTracking
import com.andyapps.enrow.domain.enumeration.HabitAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InMemoryHabitDao(
    private val db: InMemoryDatabase
) : HabitDao {
    override suspend fun insert(habit: HabitEntity) {
        db.habits.add(habit)
    }

    override suspend fun update(habit: HabitEntity) {
        db.habits.find { it.id == habit.id }?.name = habit.name
    }

    override suspend fun delete(habit: HabitEntity) {
        db.habits.removeIf { it.id == habit.id }
    }

    override fun getAllWithTrackings(): Flow<List<HabitWithTracking>> {
        return flow {
            val habits = db.habits.map { habit ->
                val trackings = db.habitTrackings.filter { it.habitId == habit.id }

                HabitWithTracking(habit, trackings)
            }

            emit(habits)
        }
    }

    override fun getAll(): Flow<List<HabitEntity>> {
        return flow { emit(db.habits) }
    }

    override suspend fun getById(id: String): HabitEntity? {
        return db.habits.firstOrNull { it.id == id }
    }

    override suspend fun getByIdWithTracking(id: String): HabitWithTracking? {
        val habit = db.habits.firstOrNull { it.id == id } ?: return null

        return HabitWithTracking(
            habit = habit,
            trackings = db.habitTrackings.filter { it.habitId == habit.id }
        )
    }
}