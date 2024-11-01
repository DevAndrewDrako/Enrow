package com.andyapps.enrow.presentation.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andyapps.enrow.application.dto.HabitLogDto
import com.andyapps.enrow.domain.enumeration.HabitEventType
import com.andyapps.enrow.presentation.ui.feature.admin.log.habit.HabitLogListItem
import java.util.Calendar

@Composable
fun HabitLogListItemView(
    item: HabitLogListItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = item.name)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = item.datetime)
            }
            Text(text = item.description)
        }
    }
}

@Preview
@Composable
private fun HabitLogListItemPreview() {
    HabitLogListItemView(
        item = HabitLogListItem(
            HabitLogDto(
                "Habit Name",
                HabitEventType.CHECKED_IN,
                "Description some long description some long description some long description some long description some long description.",
                Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }.apply { add(Calendar.MONTH, 2) }
            )
        )
    )
}