package com.andyapps.enrow.domain.error

class HabitException(
    val errorCode: HabitErrorCode
) : EnrowException("Error code: $errorCode")