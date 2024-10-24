package com.andyapps.enrow.presentation.ui.feature.habit.modify

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.shared.ObserveAsEvents
import com.andyapps.enrow.presentation.ui.shared.ObserveNavigationEvent

@Composable
fun ModifyHabitPage(
    navController: NavController,
    vm: HabitAggregateViewModel
) {
    val state by vm.modifyState.collectAsState()

    ObserveNavigationEvent(flow = vm.navigationFlow, navController = navController)

    ModifyHabitPage(state = state) {
        vm.onModifyEvent(it)
    }
}

@Composable
fun ModifyHabitPage(
    state: ModifyHabitState,
    onEvent: (ModifyHabitEvent) -> Unit
) {
    var name by remember {
        mutableStateOf(state.name)
    }
    
    Column {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            TextField(value = name, onValueChange = {
                name = it
            })
        }
        Row {
            Button(
                onClick = {
                    onEvent(ModifyHabitEvent.Create(name))
                }
            ) {
                Text(text = "Save")
            }
        }
    }
}