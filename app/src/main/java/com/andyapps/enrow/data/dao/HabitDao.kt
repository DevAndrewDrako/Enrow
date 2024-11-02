package com.andyapps.enrow.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitWithLogs
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert
    suspend fun insert(habit: HabitEntity)

    @Update
    suspend fun update(habit: HabitEntity)

    @Transaction
    @Query("SELECT * FROM HABIT WHERE IS_DELETED == 0")
    fun getAll() : Flow<List<HabitWithLogs>>

    @Query("SELECT * FROM HABIT WHERE ID = :id")
    suspend fun getById(id: String) : HabitEntity?

    @Query("SELECT * FROM HABIT WHERE ID = :id")
    suspend fun getByIdWithLogs(id: String) : HabitWithLogs?
}