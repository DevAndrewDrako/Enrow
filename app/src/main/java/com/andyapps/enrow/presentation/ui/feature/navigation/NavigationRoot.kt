package com.andyapps.enrow.presentation.ui.feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.andyapps.enrow.presentation.ui.feature.habit.HabitAggregateViewModel
import com.andyapps.enrow.presentation.ui.feature.habit.list.HabitScreen
import com.andyapps.enrow.presentation.ui.feature.habit.modify.ModifyHabitPage
import com.andyapps.enrow.presentation.ui.shared.sharedViewModel

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()

    Scaffold { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = Route.Habit.name
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
            }
        }
    }
}