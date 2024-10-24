package com.andyapps.enrow.presentation.ui.feature.habit.modify


sealed interface ModifyHabitEvent {
    data class Create(val name: String) : ModifyHabitEvent
}