package com.andyapps.enrow.presentation.di

import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.application.service.HabitLogService
import com.andyapps.enrow.application.usecase.CheckHabitUseCase
import com.andyapps.enrow.application.usecase.CreateHabitUseCase
import com.andyapps.enrow.application.usecase.DeleteHabitUseCase
import com.andyapps.enrow.application.usecase.GetAllHabitsUseCase
import com.andyapps.enrow.application.usecase.GetHabitByIdUseCase
import com.andyapps.enrow.application.usecase.GetHabitLogsUseCase
import com.andyapps.enrow.application.usecase.UpdateHabitUseCase
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.domain.repository.HabitLogRepository
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
    fun providesCreateHabitUseCase(repository: HabitRepository, logRepository: HabitLogRepository, service: HabitService) : CreateHabitUseCase {
        return CreateHabitUseCase(repository, logRepository, service)
    }
    @Provides
    fun providesGetHabitByIdUseCase(service: HabitService) : GetHabitByIdUseCase {
        return GetHabitByIdUseCase(service)
    }
    @Provides
    fun providesUpdateHabitUseCase(repository: HabitRepository, logRepository: HabitLogRepository) : UpdateHabitUseCase {
        return UpdateHabitUseCase(repository, logRepository)
    }
    @Provides
    fun providesDeleteHabitUseCase(repository: HabitRepository, logRepository: HabitLogRepository) : DeleteHabitUseCase {
        return DeleteHabitUseCase(repository, logRepository)
    }
    @Provides
    fun providesGetHabitTrackingsUseCase(service: HabitLogService) : GetHabitLogsUseCase {
        return GetHabitLogsUseCase(service)
    }
    @Provides
    fun providesCheckHabitUseCase(habitRepository: HabitRepository, habitLogRepository: HabitLogRepository, habitLogService: HabitLogService) : CheckHabitUseCase {
        return CheckHabitUseCase(habitLogRepository, habitRepository, habitLogService)
    }
}