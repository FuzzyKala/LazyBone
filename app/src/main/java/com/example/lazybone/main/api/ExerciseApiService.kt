package com.example.lazybone.main.api

import retrofit2.http.GET
import retrofit2.http.Query


// Defines the API contract and endpoints in a way Retrofit can handle.
// Wger API
interface ExerciseApiService {

    @GET("exercise")
    suspend fun getExercises(
        @Query("category") categoryId: Int,
        @Query("language") languageId: Int = 2,
        @Query("limit") limit: Int = 592
    ): WgerExerciseResponse

    @GET("exercisecategory")
    suspend fun getBodyParts(): WgerBodyPartResponse

    @GET("exerciseimage")
    suspend fun getExerciseImages(
        @Query("exercise_base") exerciseBaseId: Int
    ): WgerImageResponse
}