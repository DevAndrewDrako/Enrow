package com.andyapps.enrow.presentation.di

import android.content.Context
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.data.AppDatabase
import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.data.repository.InMemoryHabitRepository
import com.andyapps.enrow.data.service.InMemoryHabitService
import com.andyapps.enrow.domain.repository.HabitRepository
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
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.create(context)
    }
    @Provides
    @Singleton
    fun providesInMemoryDataBase() : InMemoryDatabase {
        return InMemoryDatabase()
    }
    @Provides
    fun providesInMemoryHabitService(database: InMemoryDatabase) : HabitService {
        return InMemoryHabitService(database)
    }
    @Provides
    fun providesInMemoryHabitRepository(database: InMemoryDatabase) : HabitRepository {
        return InMemoryHabitRepository(database)
    }
}