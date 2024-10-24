package com.andyapps.enrow.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitWithTracking
import kotlinx.coroutines.flow.Flow

//@Dao
interface HabitDao {
//    @Insert
    suspend fun insert(habit: HabitEntity)

//    @Update
    suspend fun update(habit: HabitEntity)

//    @Delete
    suspend fun delete(habit: HabitEntity)

    // @Query()
    fun getAllWithTrackings() : Flow<List<HabitWithTracking>>

    // @Query()
    fun getAll() : Flow<List<HabitEntity>>

    // @Query("")
    suspend fun getById(id: String) : HabitEntity?

    // @Query("")
    suspend fun getByIdWithTracking(id: String) : HabitWithTracking?
}