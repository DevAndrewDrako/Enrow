package com.andyapps.enrow.presentation.ui.feature.admin.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.andyapps.enrow.presentation.ui.feature.navigation.Route

@Composable
fun AdminScreen(
    navController: NavController
) {
    AdminScreen {
        navController.navigate(it)
    }    
}

@Composable
fun AdminScreen(
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(
                    onClick = { 
                        navigate(Route.Habit.name)
                    }
                ) {
                    Text(text = "App")
                }
                
                TextButton(onClick = {
                    navigate(Route.AdminHabitLog.name)
                }) {
                    Text(text = "Habit Tracker")
                }
            }
        }
    }
}