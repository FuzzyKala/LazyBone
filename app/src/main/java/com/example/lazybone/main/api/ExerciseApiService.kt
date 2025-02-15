package com.example.lazybone.main.api

import retrofit2.http.GET


// Defines the API contract and endpoints in a way Retrofit can handle.
// Wger API
interface ExerciseApiService {
    @GET("exerciseinfo")
    suspend fun getExercises(): WgerExerciseResponse

    @GET("exercisecategory")
    suspend fun getBodyPartList(): WgerBodyPartResponse
}