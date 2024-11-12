package com.andyapps.enrow.presentation.ui.feature.habit.list.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitListItemUiModel
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreenEvent

@Composable
fun HabitListView(
    habits: List<HabitListItemUiModel>,
    onEvent: (HabitScreenEvent) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(habits) { item ->
            HabitListItemView(item = item) {
                onEvent(HabitScreenEvent.SelectHabit(item.id))
            }
        }
    }
}