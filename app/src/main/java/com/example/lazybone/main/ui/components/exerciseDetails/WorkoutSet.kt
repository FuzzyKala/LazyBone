package com.example.lazybone.main.ui.components.exerciseDetails

import java.time.LocalDate

data class Set(
    val id: Int,
    val weight: Double,
    val reps: Int
)

data class Workout(
    val id: Int,
    val name: String,
    val sets: List<Set>
)

data class Program(
    val id: Int,
    val date: LocalDate,
    val workouts: List<Workout>
)
