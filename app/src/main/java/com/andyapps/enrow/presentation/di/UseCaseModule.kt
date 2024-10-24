package com.andyapps.enrow.presentation.di

import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.application.usecase.CreateHabitUseCase
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import com.andyapps.enrow.application.usecase.GetHabitByIdUseCase
import com.andyapps.enrow.domain.repository.HabitRepository
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
    @Provides
    fun providesCreateHabitUseCase(repository: HabitRepository, service: HabitService) : CreateHabitUseCase {
        return CreateHabitUseCase(repository, service)
    }
    @Provides
    fun providesGetHabitByIdUseCase(repository: HabitRepository) : GetHabitByIdUseCase {
        return GetHabitByIdUseCase(repository)
    }
}