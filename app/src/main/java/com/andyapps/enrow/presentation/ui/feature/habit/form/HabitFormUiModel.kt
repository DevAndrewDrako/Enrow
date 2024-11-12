package com.andyapps.enrow.presentation.ui.feature.habit.form

import com.andyapps.enrow.application.dto.HabitDto
import java.util.UUID

data class HabitFormUiModel(
    val id: UUID,
    val name: String,
) {
    companion object {
        fun createFromDto(dto: HabitDto) : HabitFormUiModel {
            return HabitFormUiModel(
                id = dto.id,
                name = dto.name,
            )
        }
    }
}