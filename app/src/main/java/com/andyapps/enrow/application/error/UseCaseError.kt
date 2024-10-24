package com.andyapps.enrow.application.error

sealed interface UseCaseError {
    enum class CreateHabit {
        ALREADY_EXISTS_BY_ID,
        ALREADY_EXISTS_BY_NAME
    }
}