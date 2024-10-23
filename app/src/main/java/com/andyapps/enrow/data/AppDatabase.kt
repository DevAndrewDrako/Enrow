package com.andyapps.enrow.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andyapps.enrow.data.dao.HabitDao
import com.andyapps.enrow.data.entity.HabitEntity

@Database(
    entities = [HabitEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao() : HabitDao

    companion object {

    }
}