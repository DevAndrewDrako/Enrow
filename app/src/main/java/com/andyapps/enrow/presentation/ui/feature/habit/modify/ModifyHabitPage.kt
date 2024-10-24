package com.andyapps.enrow.presentation.ui.feature.habit.modify

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavController
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.shared.HabitToModify
import com.andyapps.enrow.presentation.ui.shared.ObserveAsEvents
import com.andyapps.enrow.presentation.ui.shared.ObserveNavigationEvent

@Composable
fun ModifyHabitPage(
    navController: NavController,
    vm: HabitAggregateViewModel
) {
    val state by vm.state.collectAsState()

    ObserveNavigationEvent(flow = vm.navigationFlow, navController = navController)

    ModifyHabitPage(habit = state.modifyingHabit) {
        vm.onModifyEvent(it)
    }
}

@Composable
fun ModifyHabitPage(
    habit: HabitToModify,
    onEvent: (ModifyHabitEvent) -> Unit
) {
    var name by remember {
        mutableStateOf(habit.name)
    }

    Column(
        modifier = Modifier.fillMaxSize()
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
        Row {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (habit.id != null) {
                        onEvent(ModifyHabitEvent.Update(habit.id, name))
                    }
                    else {
                        onEvent(ModifyHabitEvent.Create(name))
                    }
                }
            ) {
                if (habit.id != null) {
                    Text(text = "Update")
                }
                else {
                    Text(text = "Save")
                }
            }
        }
        habit.id?.let {
            Row {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onEvent(ModifyHabitEvent.Delete(it))
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
    ModifyHabitPage(habit = HabitToModify()) {
        
    }
}