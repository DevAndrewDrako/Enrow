package com.andyapps.enrow.presentation.ui.feature.habit.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.andyapps.enrow.presentation.ui.feature.habit.HabitViewModel
import com.andyapps.enrow.presentation.ui.feature.habit.list.composables.HabitListView
import com.andyapps.enrow.presentation.ui.shared.ObserveNavigationEvent

@Composable
fun HabitScreen(
    navController: NavController,
    vm: HabitViewModel
) {
    val state by vm.state.collectAsState()

    ObserveNavigationEvent(flow = vm.navigationFlow, navController = navController)

    HabitScreenView(habits = state.habits) {
        vm.onScreenEvent(it)
    }
}

@Composable
fun HabitScreenView(
    habits: List<HabitListItemUiModel>,
    onEvent: (HabitScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            HabitListView(habits = habits) {
                onEvent(it)
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(HabitScreenEvent.AddHabit)
                }
            ) {
                Text(text = "Add Habit")
            }
        }
    }
}



@Preview
@Composable
private fun HabitScreen_Preview() {
    
}