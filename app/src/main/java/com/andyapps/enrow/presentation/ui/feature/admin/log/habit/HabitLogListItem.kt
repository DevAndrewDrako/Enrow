package com.andyapps.enrow.presentation.ui.feature.admin.log.habit

import com.andyapps.enrow.application.dto.HabitLogDto
import com.andyapps.enrow.shared.toFormat
import java.util.Calendar

data class HabitLogListItem(
    private val data: HabitLogDto
) {
    val name get() = data.eventType.name
    val description get() = data.description
    val datetime get() = data.createdAt.toFormat()
}
