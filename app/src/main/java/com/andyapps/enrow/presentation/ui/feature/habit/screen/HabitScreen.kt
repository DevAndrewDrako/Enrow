package com.andyapps.enrow.presentation.ui.feature.habit.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel

@Composable
fun HabitScreen(
    vm: HabitAggregateViewModel
) {
    val state by vm.screenState.collectAsState()
    
    HabitScreen(state = state)
}

@Composable
fun HabitScreen(
    state: HabitScreenState
) {
    Column {
        Row {
            LazyColumn {
                items(state.habits) { item ->  
                    Row {
                        Text(text = item.name)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = item.daysInRow.toString())
                    }
                }
            }
        }
    }
}