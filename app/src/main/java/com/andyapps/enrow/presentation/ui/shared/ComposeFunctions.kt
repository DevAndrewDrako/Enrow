package com.andyapps.enrow.presentation.ui.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.andyapps.enrow.presentation.ui.feature.navigation.NavigationEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navHostController: NavHostController
) : T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navHostController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}

@Composable
fun ObserveNavigationEvent(flow: Flow<NavigationEvent>, navController: NavController) {
    ObserveAsEvents(flow = flow) {
        when (it) {
            is NavigationEvent.NavigateInclusive -> {
                navController.navigate(it.name) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
            is NavigationEvent.NavigateToRoute -> navController.navigate(it.name)
            is NavigationEvent.NavigateUp -> navController.navigateUp()
        }
    }
}

@Composable
fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(onEvent)
            }
        }
    }
}