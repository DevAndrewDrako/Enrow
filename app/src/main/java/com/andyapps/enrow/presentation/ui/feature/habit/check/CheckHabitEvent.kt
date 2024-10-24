package com.andyapps.enrow.presentation.ui.feature.habit.check

import java.util.UUID

sealed interface CheckHabitEvent {
    data class Edit(val id: UUID) : CheckHabitEvent
    data class Abort(val id: UUID) : CheckHabitEvent
    data class Check(val id: UUID) : CheckHabitEvent
    data object Close : CheckHabitEvent
}