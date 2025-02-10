package com.example.lazybone.main.api

import android.util.Log


//Acts as a middle layer to abstract away the details of making API calls and managing data.
class ExerciseRepository(private val api: ExerciseApiService) {

    suspend fun fetchExercises(bodyPart: String): List<Exercise>? {
        return try {
            val response = api.getExercisesByBodyPart(bodyPart)
            Log.d("ExerciseRepository", "Raw API Response: $response")
            response
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}", e)
            null
        }
    }
}
