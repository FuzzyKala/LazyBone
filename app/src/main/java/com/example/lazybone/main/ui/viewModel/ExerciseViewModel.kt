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
            clearError()
            try {
                val bodyPartsList = _bodyParts.value

                if (bodyPartsList.isEmpty()) {
                    val errorMsg = "Body parts not loaded yet!"
                    Log.e("ExerciseViewModel", errorMsg)
                    setError(errorMsg)
                    return@launch
                }
                when (val result = repository.fetchExercises(bodyPart, bodyPartsList)) {
                    is ApiResult.Success -> _exercises.value = result.data

                    is ApiResult.Error -> {
                        val errorMsg = "Error ${result.code}: ${result.message}"
                        Log.e("ExerciseViewModel", errorMsg)
                        setError(errorMsg)
                        _exercises.value = emptyList()
                    }
                }
            } catch (e: Exception) {
                val errorMsg = "Unexpected API Error: ${e.message}"
                Log.e("ExerciseViewModel", errorMsg)
                setError(errorMsg)
                _exercises.value = emptyList()
            }
        }
    }

    fun loadExerciseImages(exerciseId: Int) {
        viewModelScope.launch {

            try {
                val exerciseList = _exercises.value

                if (exerciseList.isEmpty()) {
                    val errorMsg = "Exercises not loaded yet!"
                    Log.e("ExerciseViewModel", errorMsg)
                    setError(errorMsg)
                    return@launch
                }

                when (val result = repository.fetchExerciseImages(exerciseId, exerciseList)) {
                    is ApiResult.Success -> _exerciseImages.value = result.data
                    is ApiResult.Error -> {
                        val errorMsg = "Error ${result.code}: ${result.message}"
                        Log.e("ExerciseViewModel", errorMsg)
                        setError(errorMsg)
                        _exerciseImages.value = emptyList()
                    }
                }
            } catch (e: Exception) {
                val errorMsg = "Unexpected API Error: ${e.message}"
                Log.e("ExerciseViewModel", errorMsg)
                setError(errorMsg)
                _exerciseImages.value = emptyList()
            }
        }
    }

    fun loadBodyParts() {
        viewModelScope.launch {
            clearError()
            try {
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
            } catch (e: Exception) {
                val errorMsg = "Unexpected API Error: ${e.message}"
                Log.e("ExerciseViewModel", errorMsg)
                setError(errorMsg)
            }
        }
    }
}
