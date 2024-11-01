package com.andyapps.enrow.data.dao

import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitWithLogs
import kotlinx.coroutines.flow.Flow

//@Dao
interface HabitDao {
//    @Insert
    suspend fun insert(habit: HabitEntity)

//    @Update
    suspend fun update(habit: HabitEntity)

    // @Query()
    fun getAll() : Flow<List<HabitWithLogs>>

    // @Query("")
    suspend fun getById(id: String) : HabitEntity?

    // @Query("")
    suspend fun getByIdWithLogs(id: String) : HabitWithLogs?
}