package com.example.lazybone.main.api

import android.util.Log


//Acts as a middle layer to abstract away the details of making API calls and managing data.
class ExerciseRepository(private val api: ExerciseApiService) {

    suspend fun fetchExercises(bodyPart: String, bodyParts: List<BodyPart>): List<Exercise>? {
        return try {
            val categoryId = bodyParts.find { it.name.equals(bodyPart, ignoreCase = true) }?.id
            if (categoryId == null) {
                Log.e("ExerciseRepository", "Body not found for body part: $bodyPart")
                return emptyList()
            }

            val response: WgerExerciseResponse = api.getExercises(categoryId)
            Log.d("ExerciseRepository", "Raw API Response: $response")
            response.results
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}", e)
            null
        }
    }

    suspend fun fetchExerciseImages(
        exerciseId: Int,
        exercises: List<Exercise>
    ): List<ExerciseImage>? {
        return try {
            val exerciseBaseId = exercises.find { it.id == exerciseId }?.exerciseBaseId
            if (exerciseBaseId == null) {
                Log.e("ExerciseRepository", "Image not found for exercise: $exerciseId")
                return emptyList()
            }

            val response: WgerImageResponse = api.getExerciseImages(exerciseBaseId)
            Log.d("ExerciseRepository", "Raw API Response: $response")
            response.results
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}", e)
            null
        }
    }

    suspend fun fetchBodyPartList(): List<BodyPart>? {
        return try {
            val response: WgerBodyPartResponse = api.getBodyParts()
            Log.d("ExerciseRepository", "Raw API Response: $response")
            response.results
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}", e)
            null
        }
    }
}
