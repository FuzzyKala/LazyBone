package com.example.lazybone.main.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


//Defines the API contract and endpoints in a way Retrofit can handle.
interface ExerciseApiService {
    @Headers(
        "X-RapidAPI-Key: 18bf928921msh664498f858bba6bp1ed026jsn7b9d1cd5518b",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )

    @GET("exercises/bodyPart/{bodyPart}")
    suspend fun getExercisesByBodyPart(@Path("bodyPart") bodyPart: String): List<Exercise>?
}