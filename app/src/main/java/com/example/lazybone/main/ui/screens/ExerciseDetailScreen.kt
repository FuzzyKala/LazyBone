package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutControl
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutRecord
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutSet
import com.example.lazybone.main.ui.components.exerciseDetails.WorkoutTitle
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar


@Composable
fun ExerciseDetailScreen(navController: NavController, exerciseId: String) {
    val exerciseViewModel = LocalExerciseViewModel.current
    val exercises by exerciseViewModel.exercises.collectAsState()
    val exercise = exercises.find { it.id == exerciseId }

    var weightInput by remember { mutableStateOf("") }
    var repsInput by remember { mutableStateOf("") }
    val workoutSets = remember { mutableStateListOf<WorkoutSet>() }

    fun addSet() {
        val weight = weightInput.toDoubleOrNull() ?: return
        val reps = repsInput.toIntOrNull() ?: return

        workoutSets.add(
            WorkoutSet(
                setNumber = workoutSets.size + 1,
                weight = weight,
                reps = reps
            )
        )
    }

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
                    { weightInput = it },
                    { repsInput = it },
                    { addSet() })
                HorizontalDivider()
                WorkoutRecord(workoutSets)
            }
        }
    }
}

