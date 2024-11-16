package com.andyapps.enrow.presentation.ui.feature.habit.check

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.andyapps.enrow.presentation.ui.feature.habit.HabitViewModel
import com.andyapps.enrow.presentation.ui.shared.ObserveNavigationEvent
import com.andyapps.enrow.presentation.ui.shared.ObserveToastEvent

@Composable
fun HabitCheckView(
    navController: NavController,
    vm: HabitViewModel
) {
    val state by vm.state.collectAsState()

    ObserveToastEvent(flow = vm.toastFlow)

    ObserveNavigationEvent(flow = vm.navigationFlow, navController = navController)

    state.habitCheck?.let { habit ->
        HabitCheckView(habit = habit) {
            vm.onCheckEvent(it)
        }
    } ?: Text(text = "Not Found")
}

@Composable
fun HabitCheckView(
    habit: HabitCheckUiModel,
    onEvent: (HabitCheckEvent) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onEvent(HabitCheckEvent.Close)
                }
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "")
            }
            IconButton(
                onClick = {
                    onEvent(HabitCheckEvent.Edit(habit.id))
                }
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 180.dp)
                .weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = habit.name, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = habit.daysInRow.toString(), fontSize = 120.sp)
            }
        }

        Row {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (habit.isCheckInEnabled) {
                        onEvent(HabitCheckEvent.Check(habit.id))
                    }
                },
                enabled = habit.isCheckInEnabled
            ) {
                Text(text = "Check")    
            }
        }
        Row {
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                }
            ) {
                Text(text = "Abort habit")
            }
        }
    }
}

@Preview
@Composable
private fun CheckHabitPage_Preview() {

}