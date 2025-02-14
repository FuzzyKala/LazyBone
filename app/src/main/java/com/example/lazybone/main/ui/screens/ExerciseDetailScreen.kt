package com.example.lazybone.main.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutControl
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutGif
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutRecord
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutTitle
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.navigation.LocalWorkoutViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar


@RequiresApi(Build.VERSION_CODES.P)

@Composable
fun ExerciseDetailScreen(navController: NavController, exerciseId: String) {

    val exerciseViewModel = LocalExerciseViewModel.current
    val exercises by exerciseViewModel.exercises.collectAsState()
    val exercise = exercises.find { it.id == exerciseId }

    val workoutViewModel = LocalWorkoutViewModel.current


//    var weightInput by remember { mutableStateOf("") }
//    var repsInput by remember { mutableStateOf("") }
//    val workoutSets = remember { mutableStateListOf<WorkoutSet>() }

    val weightInput by workoutViewModel.weightInput.collectAsState()
    val repsInput by workoutViewModel.repsInput.collectAsState()
    val workoutSets by workoutViewModel.workoutSets.collectAsState()
    val currentWorkoutSets = workoutSets[exerciseId] ?: emptyList()

//    fun addSet() {
//        val weight = weightInput.toDoubleOrNull() ?: return
//        val reps = repsInput.toIntOrNull() ?: return
//
////        workoutSets.add(
////            WorkoutSet(
////                setNumber = workoutSets.size + 1,
////                weight = weight,
////                reps = reps
////            )
////        )
//        val newSet = WorkoutSet(
//            setNumber = currentWorkoutSets.size + 1,
//            weight = weight,
//            reps = reps
//        )
//        workoutViewModel.addWorkoutSet(exerciseId, newSet)
//    }

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
                    { workoutViewModel.addWorkoutSet(exerciseId) }
                )
                HorizontalDivider()
                WorkoutGif(exercise.gifUrl)
                if (workoutSets.isNotEmpty()) {
                    HorizontalDivider()
                    WorkoutRecord(currentWorkoutSets)
                }
            }
        }
    }
}

