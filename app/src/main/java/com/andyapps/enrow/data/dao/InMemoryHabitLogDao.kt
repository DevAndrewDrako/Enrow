package com.andyapps.enrow.data.dao

import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.data.entity.HabitLogEntity
import com.andyapps.enrow.data.entity.LogHabitPair
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InMemoryHabitLogDao(
    private val db: InMemoryDatabase
) : HabitLogDao {
    override suspend fun insert(log: HabitLogEntity) {
        db.habitLogs.add(log)
    }

    override fun getAll(): Flow<List<LogHabitPair>> {
        return flow {
            emit(db.habitLogs.map { tracking ->
                val habit = db.habits.first { it.id == tracking.habitId }

                LogHabitPair(habit, tracking)
            })
        }
    }

    override suspend fun getLatestLog(habitId: String, eventType: Int): LogHabitPair? {
        return db.habitLogs
            .filter { it.habitId == habitId && it.eventType == eventType }
            .sortedByDescending { it.createdAt }
            .map { log ->
                LogHabitPair(
                    habit = db.habits.first { it.id == habitId },
                    log = log
                )
            }
            .firstOrNull()
    }
}