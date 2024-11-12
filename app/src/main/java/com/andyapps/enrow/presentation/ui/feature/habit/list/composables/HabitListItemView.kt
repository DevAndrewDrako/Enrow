package com.andyapps.enrow.presentation.ui.feature.habit.list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitListItemUiModel
import java.util.UUID

@Composable
fun HabitListItemView(
    item: HabitListItemUiModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = item.daysInRow.toString())
    }
}

@Preview
@Composable
private fun HabitListItemPreview() {
    HabitListItemView(item = HabitListItemUiModel(UUID.randomUUID(), "Test habit", 5)) {

    }
}