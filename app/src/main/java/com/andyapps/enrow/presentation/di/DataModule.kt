package com.andyapps.enrow.presentation.di

import android.content.Context
import com.andyapps.enrow.data.AppDatabase
import com.andyapps.enrow.data.InMemoryDatabase
import com.andyapps.enrow.data.service.InMemoryHabitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.create(context)
    }
    @Provides
    fun providesInMemoryDataBase() : InMemoryDatabase {
        return InMemoryDatabase()
    }
    @Provides
    fun providesInMemoryHabitService(database: InMemoryDatabase) : InMemoryHabitService {
        return InMemoryHabitService(database)
    }
}