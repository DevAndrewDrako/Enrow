package com.andyapps.enrow.presentation.ui.feature.habit.check

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.shared.ObserveNavigationEvent

@Composable
fun CheckHabitPage(
    navController: NavController,
    vm: HabitAggregateViewModel
) {
    val state by vm.state.collectAsState()

    ObserveNavigationEvent(flow = vm.navigationFlow, navController = navController)

    state.displayingHabit?.let { habit ->
        CheckHabitPage(habit = habit) {
            vm.onCheckEvent(it)
        }
    }
}

@Composable
fun CheckHabitPage(
    habit: HabitDto,
    onEvent: (CheckHabitEvent) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onEvent(CheckHabitEvent.Close)
                }
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "")
            }
            IconButton(
                onClick = {
                    onEvent(CheckHabitEvent.Edit(habit.id))
                }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }

        }
        Row {
            Text(text = habit.name)
        }
        Row {
            Text(text = habit.daysInRow.toString())
        }
        
        Row {
            Button(
                onClick = {
                    
                }
            ) {
                Text(text = "Check")    
            }
        }
    }
}