package com.andyapps.enrow.data

import com.andyapps.enrow.data.entity.HabitEntity
import com.andyapps.enrow.data.entity.HabitTrackingEntity

class InMemoryDatabase {
    val habits: MutableList<HabitEntity> = mutableListOf()
    val habitTrackings: MutableList<HabitTrackingEntity> = mutableListOf()
}

