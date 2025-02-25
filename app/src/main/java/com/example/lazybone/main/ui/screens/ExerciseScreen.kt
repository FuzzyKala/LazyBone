package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.exercise.ExerciseBody
import com.example.lazybone.main.ui.components.exercise.ExerciseSearchBar
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar

@Composable
fun ExerciseScreen(navController: NavController) {
    val exerciseViewModel = LocalExerciseViewModel.current
    val bodyParts by exerciseViewModel.bodyParts.collectAsState()
    val errorMessage by exerciseViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        exerciseViewModel.loadBodyParts()
    }

    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            ExerciseSearchBar()
            if (errorMessage == null) {
                ExerciseBody(navController, bodyParts)
            } else {
                Text(text = errorMessage!!, modifier = Modifier.padding(16.dp))
            }

        }

    }
}