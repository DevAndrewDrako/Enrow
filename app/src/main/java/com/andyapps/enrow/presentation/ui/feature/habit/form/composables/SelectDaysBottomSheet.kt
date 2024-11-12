package com.andyapps.enrow.presentation.ui.feature.habit.form.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.andyapps.enrow.presentation.model.UiDay
import com.andyapps.enrow.presentation.ui.shared.SelectItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDaysBottomSheet(
    modifier: Modifier = Modifier,
    days: Set<SelectItem<UiDay>>,
    onApply: (Set<SelectItem<UiDay>>) -> Unit,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var selectedDays by remember {
        mutableStateOf(days)
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Column {
            selectedDays.forEach { day ->
                Row {
                    Text(text = day.value.asString())
                    Spacer(modifier = Modifier.weight(1f))
                    Checkbox(
                        checked = day.isSelected,
                        onCheckedChange = {
                            selectedDays = selectedDays.map { if (it == day) it.copy(isSelected = !it.isSelected) else it }.toSet()
                        }
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { 
                    scope.launch { sheetState.hide() }.invokeOnCompletion { 
                        if (!sheetState.isVisible) {
                            onApply(selectedDays)
                        }
                    }
                }
            ) {
                Text(text = "Apply")
            }
        }
    }
}