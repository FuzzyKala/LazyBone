package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutControl
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutGif
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutRecord
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutTitle
import com.example.lazybone.main.ui.navigation.LocalDateViewModel
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.navigation.LocalWProgramViewModel
import com.example.lazybone.main.ui.navigation.LocalWorkoutViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar


@Composable
fun ExerciseDetailScreen(navController: NavController, exerciseId: String) {

    val exerciseViewModel = LocalExerciseViewModel.current
    val exercises by exerciseViewModel.exercises.collectAsState()
    val exercise = exercises.find { it.id == exerciseId.toIntOrNull() }

    val exerciseImages by exerciseViewModel.exerciseImages.collectAsState()

    LaunchedEffect(exerciseId) {
        exerciseViewModel.loadExerciseImages(exerciseId.toInt())
    }

    val workoutViewModel = LocalWorkoutViewModel.current
    val programViewModel = LocalWProgramViewModel.current

    val weightInput by workoutViewModel.weightInput.collectAsState()
    val repsInput by workoutViewModel.repsInput.collectAsState()
    val workouts by workoutViewModel.workouts.collectAsState()

    val currentWorkout = workouts.find { it.name == exercise?.name }

    val currentWorkoutSets = currentWorkout?.sets ?: emptyList()

    val dateViewModel = LocalDateViewModel.current

    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (exercise != null) {
                WorkoutTitle(exercise.name)
                WorkoutControl(
                    weightInput,
                    repsInput,
                    { workoutViewModel.setWeightInput(it) },
                    { workoutViewModel.setRepsInput(it) },
                    {
                        workoutViewModel.addSetToWorkout(exercise.name)
                        val updatedWorkouts = workoutViewModel.workouts.value
                        val updatedWorkout = updatedWorkouts.find { it.name == exercise.name }
                            ?: return@WorkoutControl

                        programViewModel.addWorkoutToProgram(
                            updatedWorkout,
                            dateViewModel.today.value
                        )
                    }
                )
                HorizontalDivider()
                WorkoutGif(exerciseImages)
                if (currentWorkoutSets.isNotEmpty()) {
                    HorizontalDivider()
                    WorkoutRecord(currentWorkoutSets)
                }
            }
        }
    }
}

