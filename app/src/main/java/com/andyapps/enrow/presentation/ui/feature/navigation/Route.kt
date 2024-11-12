package com.andyapps.enrow.presentation.ui.feature.navigation

sealed class Route(val name: String) {
    data object Habit : Route("habit")
    data object HabitScreen : Route("habit_screen")
    data object HabitForm : Route("habit_form")
    data object HabitCheck : Route("habit_check")
    data object Admin : Route("admin")
    data object AdminScreen : Route("admin_screen")
    data object AdminHabitLog : Route("admin_habit_log")
}