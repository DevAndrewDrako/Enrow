package com.andyapps.enrow.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.andyapps.enrow.data.entity.HabitTrackingEntity

//@Dao
interface HabitTrackingDao {
//    @Insert
    suspend fun insert(tracking: HabitTrackingEntity)

//    @Delete
    suspend fun delete(tracking: HabitTrackingEntity)
}