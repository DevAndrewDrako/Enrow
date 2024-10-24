package com.andyapps.enrow.presentation.ui.feature.habit.modify

import java.util.UUID


sealed interface ModifyHabitEvent {
    data class Create(val name: String) : ModifyHabitEvent
    data class Update(val id: UUID, val name: String) : ModifyHabitEvent
    data class Delete(val id: UUID) : ModifyHabitEvent
}