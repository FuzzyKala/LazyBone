package com.example.lazybone.main.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutViewModel : ViewModel(){
    private val _weightInput = MutableStateFlow("")
    val weightInput = _weightInput.asStateFlow()

    private val _repsInput = MutableStateFlow("")
    val repsInput = _repsInput.asStateFlow()

    private val _workoutSets = MutableStateFlow<Map<String, List<WorkoutSet>>>(emptyMap())
    val workoutSets = _workoutSets.asStateFlow()

    fun setWeightInput(value: String) {
        _weightInput.value = value
    }

    fun setRepsInput(value: String) {
        _repsInput.value = value
    }

    fun addWorkoutSet(exerciseId: String) {
        val weight = _weightInput.value.toDoubleOrNull() ?: return
        val reps = _repsInput.value.toIntOrNull() ?: return
        val currentSets = _workoutSets.value.toMutableMap()
        val setsForExercise = currentSets[exerciseId]?.toMutableList() ?: mutableListOf()

        // Create a WorkoutSet instance
        val set = WorkoutSet(
            setNumber = setsForExercise.size + 1,
            weight = weight,
            reps = reps
        )
        setsForExercise.add(set)
        currentSets[exerciseId] = setsForExercise
        _workoutSets.value = currentSets
    }

    fun getWorkoutSets(exerciseId: String): List<WorkoutSet> {
        return _workoutSets.value[exerciseId] ?: emptyList()
    }
}