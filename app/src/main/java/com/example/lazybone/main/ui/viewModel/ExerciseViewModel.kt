package com.example.lazybone.main.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazybone.main.api.Exercise
import com.example.lazybone.main.api.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//Holds and manages UI data in a lifecycle-aware way.
class ExerciseViewModel(private val repository: ExerciseRepository) : ViewModel() {
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises = _exercises.asStateFlow()
    fun loadExercises(bodyPart: String) {
        viewModelScope.launch {
            try {
                val result = repository.fetchExercises(bodyPart)
                Log.d("ExerciseViewModel", "API Response: $result")
                _exercises.value = result ?: emptyList()
            } catch (e: Exception) {
                Log.e("ExerciseViewModel", "API Error: ${e.message}")
            }
        }
    }

    private val _bodyParts = MutableStateFlow<List<String>>(emptyList())
    val bodyParts = _bodyParts.asStateFlow()
    fun loadBodyParts() {
        viewModelScope.launch {
            try {
                val result = repository.fetchBodyPartList()
                Log.d("ExerciseViewModel", "API Response: $result")
                _bodyParts.value = result ?: emptyList()
            } catch (e: Exception) {
                Log.e("ExerciseViewModel", "API Error: ${e.message}")
            }
        }
    }
}
