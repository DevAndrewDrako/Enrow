package com.andyapps.enrow.data

import com.andyapps.enrow.domain.entity.Habit

data class InMemoryDatabase(
    val habits: MutableList<Habit> = mutableListOf()
)