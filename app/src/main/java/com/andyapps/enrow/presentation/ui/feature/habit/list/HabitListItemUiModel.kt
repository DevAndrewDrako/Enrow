package com.andyapps.enrow.presentation.ui.feature.habit.list

import com.andyapps.enrow.application.dto.HabitDto
import java.util.UUID

data class HabitListItemUiModel(
    val id: UUID,
    val name: String,
    val daysInRow: Int
) {
    companion object {
        fun createFromDto(dto: HabitDto) : HabitListItemUiModel {
            return HabitListItemUiModel(
                id = dto.id,
                name = dto.name,
                daysInRow = dto.daysInRow()
            )
        }
    }
}
