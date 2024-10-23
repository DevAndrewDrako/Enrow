package com.andyapps.enrow.presentation.ui.feature.habit.list

import java.util.UUID

sealed interface HabitScreenEvent {
    data object AddHabit : HabitScreenEvent
    data class SelectHabit(val id: UUID) : HabitScreenEvent
}