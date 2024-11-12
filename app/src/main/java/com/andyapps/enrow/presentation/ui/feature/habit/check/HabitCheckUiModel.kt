package com.andyapps.enrow.presentation.ui.feature.habit.check

import com.andyapps.enrow.application.dto.HabitDto
import java.util.UUID

data class HabitCheckUiModel(
    val id: UUID,
    val name: String,
    val daysInRow: Int
) {
    companion object {
        fun createFromDto(dto: HabitDto) : HabitCheckUiModel {
            return HabitCheckUiModel(
                id = dto.id,
                name = dto.name,
                daysInRow = dto.daysInRow()
            )
        }
    }
}
