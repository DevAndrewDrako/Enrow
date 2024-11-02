package com.andyapps.enrow.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andyapps.enrow.data.dao.HabitDao
import com.andyapps.enrow.data.dao.HabitLogDao
import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitLogEntity
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [HabitEntity::class, HabitLogEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao() : HabitDao
    abstract fun habitLogDao() : HabitLogDao

    companion object {
        fun create(@ApplicationContext context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "ENROW_DB"
            ).build()
        }
    }
}