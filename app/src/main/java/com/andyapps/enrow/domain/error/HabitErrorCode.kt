package com.andyapps.enrow.domain.error

enum class HabitErrorCode : ErrorCode {
    NAME_IS_BLANK_OR_EMPTY,
    NAME_IS_TOO_LONG,
    AT_LEAST_ONE_DAY_IS_NOT_SELECTED,
    INCORRECTLY_CHOSEN_DAYS
}