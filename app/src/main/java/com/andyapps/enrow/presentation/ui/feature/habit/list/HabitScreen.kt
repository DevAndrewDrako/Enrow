package com.andyapps.enrow.presentation.ui.feature.habit.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.shared.ObserveAsEvents

@Composable
fun HabitScreen(
    navController: NavController,
    vm: HabitAggregateViewModel
) {
    val state by vm.listState.collectAsState()

    ObserveAsEvents(flow = vm.navigationFlow) { route ->
        navController.navigate(route.name)
    }

    HabitScreen(state = state) {
        vm.onScreenEvent(it)
    }
}

@Composable
fun HabitScreen(
    state: HabitScreenState,
    onEvent: (HabitScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn {
                items(state.habits) { item ->  
                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                onEvent(HabitScreenEvent.SelectHabit(item.id))
                            }
                    ) {
                        Text(text = item.name)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = item.daysInRow.toString())
                    }
                }
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