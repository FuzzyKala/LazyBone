package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar

@Composable
fun ExerciseScreen(
    navController: NavController
) {
    val exerciseViewModel = LocalExerciseViewModel.current
    val exercises by exerciseViewModel.exercises.collectAsState()

    LaunchedEffect(Unit) {
        exerciseViewModel.loadExercises("back")
    }

    Scaffold(
        topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Exercises Screen",
            )
            if (exercises.isEmpty()) {
                Text(text = "No exercises found")
            } else {
                exercises.forEach { exercise ->
                    Text(text = "Name: ${exercise.name}")
                    Text(text = "Target: ${exercise.target}")
                    Text(text = "Equipment: ${exercise.equipment}")
                }
            }
        }

    }
}