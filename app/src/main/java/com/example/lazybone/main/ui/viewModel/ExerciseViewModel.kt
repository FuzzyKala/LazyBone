package com.example.lazybone.main.ui.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.lazybone.main.api.ApiResult
import com.example.lazybone.main.api.BodyPart
import com.example.lazybone.main.api.Exercise
import com.example.lazybone.main.api.ExerciseImage
import com.example.lazybone.main.api.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


// Holds and manages UI data in a lifecycle-aware way.

class ExerciseViewModel(private val repository: ExerciseRepository) :
    BaseViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises = _exercises.asStateFlow()

    private val _bodyParts = MutableStateFlow<List<BodyPart>>(emptyList())
    val bodyParts = _bodyParts.asStateFlow()

    private val _exerciseImages = MutableStateFlow<List<ExerciseImage>>(emptyList())
    val exerciseImages = _exerciseImages.asStateFlow()

    fun loadExercises(bodyPart: String) {
        viewModelScope.launch {
            try {
                val bodyPartsList = _bodyParts.value

                if (bodyPartsList.isEmpty()) {
                    Log.e("ExerciseViewModel", "Body parts not loaded yet!")
                    return@launch
                }

                val result: List<Exercise>? = repository.fetchExercises(bodyPart, bodyPartsList)
                Log.d("ExerciseViewModel", "API Response: $result")
                _exercises.value = result ?: emptyList()
            } catch (e: Exception) {
                Log.e("ExerciseViewModel", "API Error: ${e.message}")
            }
        }
    }

    fun loadExerciseImages(exerciseId: Int) {
        viewModelScope.launch {
            try {
                val exerciseList = _exercises.value

                if (exerciseList.isEmpty()) {
                    Log.e("ExerciseViewModel", "Exercises not loaded yet!")
                    return@launch
                }

                val result: List<ExerciseImage>? =
                    repository.fetchExerciseImages(exerciseId, exerciseList)
                Log.d("ExerciseViewModel", "API Response: $result")
                _exerciseImages.value = result ?: emptyList()
            } catch (e: Exception) {
                Log.e("ExerciseViewModel", "API Error: ${e.message}")
            }
        }
    }

    fun loadBodyParts() {
        viewModelScope.launch {
            clearError()
            when (val result = repository.fetchBodyPartList()) {
                is ApiResult.Success -> _bodyParts.value = result.data
                is ApiResult.Error -> {
                    val errorMessage = when (result.code) {
                        400 -> "Bad Request - Invalid data"
                        401 -> "Unauthorized - Please log in"
                        403 -> "Forbidden - Access denied"
                        404 -> "Not Found - Check the endpoint"
                        500 -> "Server error - Try again later"
                        -1 -> result.message
                        else -> "Unexpected error: ${result.message}"
                    }
                    setError(errorMessage)
                    Log.e("ExerciseViewModel", "API Error: $errorMessage")
                }
            }
        }
    }
}
