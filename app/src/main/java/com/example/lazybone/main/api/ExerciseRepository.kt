package com.example.lazybone.main.api

import android.util.Log


//Acts as a middle layer to abstract away the details of making API calls and managing data.
class ExerciseRepository(private val api: ExerciseApiService) {

    suspend fun fetchExercises(bodyPart: String): List<Exercise>? {
        return try {
            val response: WgerExerciseResponse = api.getExercises()
            Log.d("ExerciseRepository", "Raw API Response: $response")
            response.results.filter { it.category.name == bodyPart }
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}", e)
            null
        }
    }

    suspend fun fetchBodyPartList(): List<BodyPart>? {
        return try {
            val response: WgerBodyPartResponse = api.getBodyPartList()
            Log.d("ExerciseRepository", "Raw API Response: $response")
            response.results
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}", e)
            null
        }
    }
}
