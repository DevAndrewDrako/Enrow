package com.andyapps.enrow.presentation.ui.feature.habit.menu

import java.util.UUID

sealed interface HabitScreenEvent {
    data object AddHabit : HabitScreenEvent
    data class SelectHabit(val id: UUID) : HabitScreenEvent
}