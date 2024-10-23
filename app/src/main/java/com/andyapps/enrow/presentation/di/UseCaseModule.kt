package com.andyapps.enrow.presentation.di

import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetAllHabitsUseCase(service: HabitService) : GetAllHabitsUseCase {
        return GetAllHabitsUseCase(service)
    }
}