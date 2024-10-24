package com.andyapps.enrow.presentation.ui.feature.habit.check

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.andyapps.enrow.application.dto.HabitDto
import com.andyapps.enrow.domain.entity.Habit
import com.andyapps.enrow.domain.valueobject.SelectedDaysSet
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.shared.ObserveNavigationEvent
import java.util.UUID

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
                Text(text = habit.selectedDays, fontSize = 60.sp)
            }
        }

        Row {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(CheckHabitEvent.Check(habit.id))
                }
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
    CheckHabitPage(habit = HabitDto(
        id = UUID.randomUUID(),
        name = "Some test name",
        selectedDays = SelectedDaysSet.ONLY_WORKING_DAYS
    )) {

    }
}