package com.andyapps.enrow.presentation.ui.feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.andyapps.enrow.presentation.ui.feature.admin.menu.AdminScreen
import com.andyapps.enrow.presentation.ui.feature.admin.log.habit.HabitLogScreen
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.feature.habit.check.CheckHabitPage
import com.andyapps.enrow.presentation.ui.feature.habit.menu.HabitScreen
import com.andyapps.enrow.presentation.ui.feature.habit.modify.ModifyHabitPage
import com.andyapps.enrow.presentation.ui.shared.sharedViewModel

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
    ) { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Route.Admin.name
        ) {
            navigation(
                startDestination = Route.HabitScreen.name,
                route = Route.Habit.name
            ) {
                composable(Route.HabitScreen.name) { entry ->
                    val viewModel = entry.sharedViewModel<HabitAggregateViewModel>(navController)

                    HabitScreen(navController, viewModel)
                }
                composable(Route.ModifyHabit.name) { entry ->
                    val viewModel = entry.sharedViewModel<HabitAggregateViewModel>(navController)

                    ModifyHabitPage(navController, viewModel)
                }
                composable(Route.CheckHabit.name) { entry ->
                    val viewModel = entry.sharedViewModel<HabitAggregateViewModel>(navController)

                    CheckHabitPage(navController, viewModel)
                }
            }
            navigation(
                startDestination = Route.AdminScreen.name,
                route = Route.Admin.name
            ) {
                composable(Route.AdminScreen.name) { entry ->
//                    val viewModel = entry.sharedViewModel<HabitAggregateViewModel>(navController)

                    AdminScreen(navController)
                }
                
                composable(Route.AdminHabitLog.name) {
                    HabitLogScreen(navController = navController)
                }
            }
        }
    }
}