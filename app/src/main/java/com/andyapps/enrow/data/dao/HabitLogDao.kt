package com.andyapps.enrow.data.dao

import com.andyapps.enrow.data.entity.HabitLogEntity
import com.andyapps.enrow.data.entity.LogHabitPair
import kotlinx.coroutines.flow.Flow

//@Dao
interface HabitLogDao {
//    @Insert
    suspend fun insert(log: HabitLogEntity)

//    @Query
    fun getAll() : Flow<List<LogHabitPair>>

    suspend fun getLatestLog(habitId: String, eventType: Int) : LogHabitPair?
}