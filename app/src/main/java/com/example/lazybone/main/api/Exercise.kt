package com.example.lazybone.main.api

// Rapid API
data class Exercise(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: String,
    val name: String,
    val target: String,
    val secondaryMuscles: List<String>,
    val instructions: List<String>
)

// Wger API