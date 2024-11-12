package com.andyapps.enrow.presentation.ui.feature.habit.check

import java.util.UUID

sealed interface HabitCheckEvent {
    data class Edit(val id: UUID) : HabitCheckEvent
    data class Abort(val id: UUID) : HabitCheckEvent
    data class Check(val id: UUID) : HabitCheckEvent
    data object Close : HabitCheckEvent
}