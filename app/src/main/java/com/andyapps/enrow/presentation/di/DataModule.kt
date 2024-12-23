package com.andyapps.enrow.presentation.di

import android.content.Context
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.application.service.HabitLogService
import com.andyapps.enrow.data.AppDatabase
import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.data.dao.HabitDao
import com.andyapps.enrow.data.dao.HabitLogDao
import com.andyapps.enrow.data.dao.InMemoryHabitDao
import com.andyapps.enrow.data.dao.InMemoryHabitLogDao
import com.andyapps.enrow.data.repository.DbHabitRepository
import com.andyapps.enrow.data.repository.DbHabitLogRepository
import com.andyapps.enrow.data.service.DbHabitService
import com.andyapps.enrow.data.service.DbHabitLogService
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.domain.repository.HabitLogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesInMemoryDataBase() : InMemoryDatabase {
        return InMemoryDatabase()
    }
    @Provides
    fun providesInMemoryHabitDao(db: InMemoryDatabase) : HabitDao {
        return InMemoryHabitDao(db)
    }
    @Provides
    fun providesInMemoryHabitTrackingDao(db: InMemoryDatabase) : HabitLogDao {
        return InMemoryHabitLogDao(db)
    }

    @Provides
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.create(context)
    }
    @Provides
    fun providesDbHabitService(db: AppDatabase) : HabitService {
        return DbHabitService(db.habitDao())
    }
    @Provides
    fun providesDbHabitLogService(db: AppDatabase) : HabitLogService {
        return DbHabitLogService(db.habitLogDao())
    }
    @Provides
    fun providesDbHabitRepository(db: AppDatabase) : HabitRepository {
        return DbHabitRepository(db.habitDao())
    }
    @Provides
    fun providesDbHabitLogRepository(db: AppDatabase) : HabitLogRepository {
        return DbHabitLogRepository(db.habitLogDao())
    }
}