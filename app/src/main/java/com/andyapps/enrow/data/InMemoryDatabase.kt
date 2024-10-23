package com.andyapps.enrow.data

import com.andyapps.enrow.domain.entity.Habit

data class InMemoryDatabase(
    val habits: MutableList<Habit> = mutableListOf(
        Habit.create("Working out"),
        Habit.create("No Smoking"),
        Habit.create("Drink 4 L in a day"),
    )
)