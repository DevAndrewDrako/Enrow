package com.andyapps.enrow.application.usecase

import com.andyapps.enrow.domain.error.HabitErrorCode
import com.andyapps.enrow.shared.Res
import com.andyapps.enrow.shared.toLocalDateTime
import com.andyapps.enrow.shared.toMilliseconds
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class ValidateHabitUseCaseTest {
    private lateinit var validateHabit: ValidateHabitUseCase

    @Before
    fun setup() {
        validateHabit = ValidateHabitUseCase()
    }

    @Test
    fun `All parameters are valid, success`() {
        val name = "Test"
        val checkInDays = "1111111"
        val timeInMillis = LocalDateTime.now().toMilliseconds()

        val result = validateHabit.execute(name, checkInDays, timeInMillis)

        assertThat(result).isInstanceOf(Res.Success::class.java)
    }

    @Test
    fun `Name is empty, error`() {
        val name = ""
        val checkInDays = "1111111"
        val timeInMillis = LocalDateTime.now().toMilliseconds()

        val result = validateHabit.execute(name, checkInDays, timeInMillis)

        assertThat(result).isInstanceOf(Res.Error::class.java)

        val error = result as Res.Error
        assertThat(error.error).isEqualTo(HabitErrorCode.NAME_IS_BLANK_OR_EMPTY)
    }

    @Test
    fun `Name is blank, error`() {
        val name = "    "
        val checkInDays = "1111111"
        val timeInMillis = LocalDateTime.now().toMilliseconds()

        val result = validateHabit.execute(name, checkInDays, timeInMillis)

        assertThat(result).isInstanceOf(Res.Error::class.java)

        val error = result as Res.Error
        assertThat(error.error).isEqualTo(HabitErrorCode.NAME_IS_BLANK_OR_EMPTY)
    }

    @Test
    fun `Name is too long, error`() {
        val name = "too long name for test too long name for test too long name for test"
        val checkInDays = "1111111"
        val timeInMillis = LocalDateTime.now().toMilliseconds()

        val result = validateHabit.execute(name, checkInDays, timeInMillis)

        assertThat(result).isInstanceOf(Res.Error::class.java)

        val error = result as Res.Error
        assertThat(error.error).isEqualTo(HabitErrorCode.NAME_IS_TOO_LONG)
    }

    @Test
    fun `Check in days are not selected, error`() {
        val name = "Test"
        val checkInDays = "0000000"
        val timeInMillis = LocalDateTime.now().toMilliseconds()

        val result = validateHabit.execute(name, checkInDays, timeInMillis)

        assertThat(result).isInstanceOf(Res.Error::class.java)

        val error = result as Res.Error
        assertThat(error.error).isEqualTo(HabitErrorCode.AT_LEAST_ONE_DAY_IS_NOT_SELECTED)
    }

    @Test
    fun `Check in days are incorrect selected, error`() {
        val name = "Test"
        val checkInDays = "00"
        val timeInMillis = LocalDateTime.now().toMilliseconds()

        val result = validateHabit.execute(name, checkInDays, timeInMillis)

        assertThat(result).isInstanceOf(Res.Error::class.java)

        val error = result as Res.Error
        assertThat(error.error).isEqualTo(HabitErrorCode.INCORRECTLY_CHOSEN_DAYS)
    }

    @Test
    fun `Zero milliseconds, success`() {
        val name = "Test"
        val checkInDays = "1111111"
        val timeInMillis: Long = 0

        val dateFromZero = 0.toLong().toLocalDateTime()

        val result = validateHabit.execute(name, checkInDays, timeInMillis)

        assertThat(result).isInstanceOf(Res.Success::class.java)

        val success = result as Res.Success
        assertThat(success.data.createdAt).isEqualTo(dateFromZero)
    }
}