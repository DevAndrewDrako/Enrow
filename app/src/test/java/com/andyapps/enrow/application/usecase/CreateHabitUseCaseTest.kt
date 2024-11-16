package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.application.error.UseCaseError
import com.andyapps.enrow.application.service.HabitService
import com.andyapps.enrow.data.repository.TestHabitLogRepository
import com.andyapps.enrow.data.repository.TestHabitRepository
import com.andyapps.enrow.data.service.TestHabitService
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.repository.HabitLogRepository
import com.andyapps.enrow.domain.repository.HabitRepository
import com.andyapps.enrow.domain.valueobject.CheckInDaysSet
import com.andyapps.enrow.shared.Res
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class CreateHabitUseCaseTest {
    private lateinit var repository: HabitRepository
    private lateinit var logRepository: HabitLogRepository
    private lateinit var service: HabitService

    private lateinit var createHabitUseCase: CreateHabitUseCase

    private val habits = mutableListOf<Habit>()

    @Before
    fun setup() {
        repository = TestHabitRepository(habits)
        logRepository = TestHabitLogRepository()
        service = TestHabitService(habits)

        createHabitUseCase = CreateHabitUseCase(repository, logRepository, service)
    }

    @Test
    fun `When habit created, returns success`() {
        val habit = Habit.create("Test", CheckInDaysSet.ALL_DAYS, 0)

        runBlocking {
            val result = createHabitUseCase.execute(habit)

            assertThat(result).isInstanceOf(Res.Success::class.java)

            val isExists = service.existsById(habit.id)
            assertThat(isExists).isTrue()
        }
    }

    @Test
    fun `When habit already exists by id, returns error`() {
        val habit = Habit.create("Test", CheckInDaysSet.ALL_DAYS, 0)

        runBlocking {
            createHabitUseCase.execute(habit)

            val isExists = service.existsById(habit.id)

            val result = createHabitUseCase.execute(habit)

            assertThat(result).isInstanceOf(Res.Error::class.java)
            assertThat(isExists).isTrue()
            assertThat((result as Res.Error).error).isEqualTo(UseCaseError.CreateHabit.ALREADY_EXISTS_BY_ID)
        }
    }
}