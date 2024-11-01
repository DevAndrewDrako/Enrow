package com.andyapps.enrow.presentation.ui.feature.admin.log.habit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andyapps.enrow.presentation.ui.shared.HabitLogListItemView

@Composable
fun HabitLogScreen(
    navController: NavController,
    vm: HabitLogScreenViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()

    HabitLogScreen(state = state) {
        vm.onEvent(it)
    }
}

@Composable
fun HabitLogScreen(
    state: HabitLogScreenState,
    onEvent: (HabitLogScreenEvent) -> Unit
) {
    Column {
        Row {
            LazyColumn {
                items(state.logs) { item ->
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    HabitLogListItemView(
                        HabitLogListItem(item)
                    )
                }
            }
        }
    }
}