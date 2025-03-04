package com.example.lazybone.main.api

import android.util.Log
import retrofit2.HttpException


// Acts as a middle layer to abstract away the details of making API calls and managing data.
class ExerciseRepository(private val api: ExerciseApiService) {

    suspend fun fetchExercises(
        bodyPart: String,
        bodyParts: List<BodyPart>
    ): ApiResult<List<Exercise>> {
        return try {
            val categoryId = bodyParts.find { it.name.equals(bodyPart, ignoreCase = true) }?.id
            if (categoryId == null) {
                Log.e("ExerciseRepository", "Body not found for body part: $bodyPart")
                return ApiResult.Error(404, "Body part not found: $bodyPart")
            }

            val response: WgerExerciseResponse = api.getExercises(categoryId)
            Log.d("ExerciseRepository", "Raw API Response: $response")
            ApiResult.Success(response.results)
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}")
            ApiResult.Error(-1, e.localizedMessage ?: "Unknown API error")
        }
    }

    suspend fun fetchExerciseImages(
        exerciseId: Int,
        exercises: List<Exercise>
    ): ApiResult<List<ExerciseImage>> {
        return try {
            val exerciseBaseId = exercises.find { it.id == exerciseId }?.exerciseBaseId
            if (exerciseBaseId == null) {
                Log.e("ExerciseRepository", "Image not found for exercise: $exerciseId")
                return ApiResult.Error(404, "exerciseBaseId not found: $exerciseBaseId")
            }

            val response: WgerImageResponse = api.getExerciseImages(exerciseBaseId)
            Log.d("ExerciseRepository", "Raw API Response: $response")
            ApiResult.Success(response.results)
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}", e)
            ApiResult.Error(-1, e.localizedMessage ?: "Unknown API error")
        }
    }

    suspend fun fetchBodyPartList(): ApiResult<List<BodyPart>> {
        return try {
            val response: WgerBodyPartResponse = api.getBodyParts()
            Log.d("ExerciseRepository", "Raw API Response: $response")
            ApiResult.Success(response.results)
        } catch (e: HttpException) {
            Log.e("ExerciseRepository", "HTTP Error: ${e.code()} - ${e.message()}")
            ApiResult.Error(e.code(), "HTTP Error: ${e.message()}")
        } catch (e: Exception) {
            Log.e("ExerciseRepository", "API Call Failed: ${e.message}")
            ApiResult.Error(-1, "Network Error: ${e.message}")
        }
    }
}
