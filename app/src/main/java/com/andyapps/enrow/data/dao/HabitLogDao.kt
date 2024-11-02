package com.andyapps.enrow.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andyapps.enrow.data.entity.HabitLogEntity
import com.andyapps.enrow.data.entity.LogHabitPair
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitLogDao {
    @Insert
    suspend fun insert(log: HabitLogEntity)

    @Query("""
        SELECT * FROM HABIT_LOG
        LEFT JOIN HABIT ON HABIT_LOG.HABIT_ID = HABIT.ID
    """)
    fun getAll() : Flow<List<LogHabitPair>>

    @Query("""
        SELECT * FROM HABIT_LOG
        LEFT JOIN HABIT ON HABIT_LOG.HABIT_ID = HABIT.ID
        WHERE HABIT.ID = :habitId AND HABIT_LOG.EVENT_TYPE = :eventType
    """)
    suspend fun getLatestLog(habitId: String, eventType: Int) : LogHabitPair?
}