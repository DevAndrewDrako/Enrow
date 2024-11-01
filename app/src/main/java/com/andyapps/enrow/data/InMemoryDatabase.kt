package com.andyapps.enrow.data

import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitLogEntity

class InMemoryDatabase {
    val habits: MutableList<HabitEntity> = mutableListOf()
    val habitLogs: MutableList<HabitLogEntity> = mutableListOf()
}

