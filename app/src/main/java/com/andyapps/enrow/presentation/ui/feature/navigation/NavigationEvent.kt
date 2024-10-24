package com.andyapps.enrow.presentation.ui.feature.navigation

sealed interface NavigationEvent {
    data class NavigateToRoute(val name: String) : NavigationEvent
    data class NavigateInclusive(val name: String) : NavigationEvent
    data object NavigateUp : NavigationEvent
}
