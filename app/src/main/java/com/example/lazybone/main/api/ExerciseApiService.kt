package com.example.lazybone.main.api

import retrofit2.http.GET
import retrofit2.http.Path

// Rapid API
// Defines the API contract and endpoints in a way Retrofit can handle.
interface ExerciseApiService {

    @GET("/exercises/bodyPart/{bodyPart}")
    suspend fun getExercisesByBodyPart(@Path("bodyPart") bodyPart: String): List<Exercise>?

    @GET("/exercises/bodyPartList")
    suspend fun getBodyPartList(): List<String>?
}

// Wger API