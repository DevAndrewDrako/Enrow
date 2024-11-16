package com.andyapps.enrow.presentation.ui.feature.habit.form

import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.presentation.model.UiDay
import com.andyapps.enrow.presentation.ui.shared.Filler
import com.andyapps.enrow.presentation.ui.shared.SelectItem
import java.time.DayOfWeek
import java.util.UUID

sealed class HabitFormUiModel(
    open val name: String,
    open val selectedDays: Set<SelectItem<UiDay>>
) {
    data class Create(
        override val name: String,
        override val selectedDays: Set<SelectItem<UiDay>>
    ) : HabitFormUiModel(name, selectedDays) {
        companion object {
            fun default() : Create {
                return Create(
                    name = "",
                    selectedDays = Filler.fillSelectedDays()
                )
            }
        }
    }

    data class Update(
        val id: UUID,
        override val name: String,
        override val selectedDays: Set<SelectItem<UiDay>>
    ) : HabitFormUiModel(name, selectedDays) {
        companion object {
            fun fromDto(dto: HabitDto) : Update {
                val selectedDays = DayOfWeek.entries.map { day ->
                    val isSelected = dto.isCheckInDay(day)

                    return@map SelectItem(UiDay(day), isSelected)
                }

                return Update(
                    id = dto.id,
                    name = dto.name,
                    selectedDays = selectedDays.toSet()
                )
            }
        }
    }
}