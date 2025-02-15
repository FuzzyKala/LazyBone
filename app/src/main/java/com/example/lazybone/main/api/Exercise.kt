package com.example.lazybone.main.api

data class WgerBodyPartResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BodyPart>
)

data class BodyPart(
    val id: Int,
    val name: String
)

data class WgerExerciseResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Exercise>
)

data class Exercise(
    val id: Int,
    val name: String,
    val category: BodyPart,
    val images: List<MovementImage>
)

data class MovementImage(
    val id: Int,
    val image: String
)