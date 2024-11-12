package com.andyapps.enrow.presentation.ui.feature.habit

import com.andyapps.enrow.presentation.ui.feature.habit.check.HabitCheckUiModel
import com.andyapps.enrow.presentation.ui.feature.habit.form.HabitFormUiModel
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitListItemUiModel

data class HabitState(
    val habits: List<HabitListItemUiModel> = emptyList(),
    val habitForm: HabitFormUiModel = HabitFormUiModel.Create.default(),
    val habitCheck: HabitCheckUiModel? = null
)