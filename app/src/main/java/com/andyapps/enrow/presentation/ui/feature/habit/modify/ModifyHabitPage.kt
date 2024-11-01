package com.andyapps.enrow.presentation.ui.feature.habit.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.shared.ObserveAsEvents
import com.andyapps.enrow.presentation.ui.shared.ObserveNavigationEvent
import com.andyapps.enrow.presentation.ui.shared.ObserveToastEvent

@Composable
fun ModifyHabitPage(
    navController: NavController,
    vm: HabitAggregateViewModel
) {
    val state by vm.state.collectAsState()

    ObserveToastEvent(flow = vm.toastFlow)

    ObserveNavigationEvent(flow = vm.navigationFlow, navController = navController)

    ModifyHabitPage(habit = state.selectedHabit) {
        vm.onModifyEvent(it)
    }
}

@Composable
fun ModifyHabitPage(
    habit: HabitDto? = null,
    onEvent: (ModifyHabitEvent) -> Unit
) {
    var name by remember {
        mutableStateOf(habit?.name ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text(text = "Name")
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedButton(
                modifier = Modifier.width(180.dp),
                onClick = {
                    onEvent(ModifyHabitEvent.Cancel)
                }
            ) {
                Text(text = "Cancel")
            }
            Button(
                modifier = Modifier.width(180.dp),
                onClick = {
                    if (habit != null) {
                        onEvent(ModifyHabitEvent.Update(habit.id, name))
                    }
                    else {
                        onEvent(ModifyHabitEvent.Create(name))
                    }
                }
            ) {
                if (habit != null) {
                    Text(text = "Update")
                }
                else {
                    Text(text = "Save")
                }
            }
        }
        habit?.let {
            Row {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(ModifyHabitEvent.Delete(it.id))
                    }
                ) {
                    Text(text = "Delete")
                }
            }
        }
    }
}

@Preview
@Composable
private fun ModifyHabitPage_Preview() {
    ModifyHabitPage {
        
    }
}