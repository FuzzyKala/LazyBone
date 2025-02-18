package com.example.lazybone.main.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.lazybone.main.ui.components.exerciseDetails.Set
import com.example.lazybone.main.ui.components.exerciseDetails.Workout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutViewModel : ViewModel() {
    private val _weightInput = MutableStateFlow("")
    val weightInput = _weightInput.asStateFlow()

    private val _repsInput = MutableStateFlow("")
    val repsInput = _repsInput.asStateFlow()

    private val _workouts = MutableStateFlow<List<Workout>>(emptyList())
    val workouts = _workouts.asStateFlow()

    fun setWeightInput(value: String) {
        _weightInput.value = value
    }

    fun setRepsInput(value: String) {
        _repsInput.value = value
    }

    fun addSetToWorkout(exerciseName: String) {
        val workoutId = _workouts.value.find { it.name == exerciseName }?.id
        val weight = _weightInput.value.toDoubleOrNull() ?: return
        val reps = _repsInput.value.toIntOrNull() ?: return

        if (workoutId != null) {
            _workouts.value = _workouts.value.map { workout ->
                if (workout.id == workoutId) {
                    val newSet = Set(
                        id = workout.sets.size + 1,
                        weight = weight,
                        reps = reps
                    )
                    Log.d("WorkoutUpdate", "Adding set: $newSet to workout: ${workout.name}")
                    workout.copy(sets = workout.sets + newSet)
                } else workout
            }
        } else {
            val newWorkout = Workout(
                id = _workouts.value.size + 1,
                name = exerciseName,
                sets = listOf(
                    Set(
                        id = 1,
                        weight = weight,
                        reps = reps
                    )
                )
            )
            _workouts.value += newWorkout
            Log.d("WorkoutUpdate", "Created new workout: $newWorkout")
        }
        Log.d("UpdatedWorkouts", "${_workouts.value}")
    }

}