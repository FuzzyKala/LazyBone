package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
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
import com.example.lazybone.main.ui.components.ExerciseSearchBar
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar

@Composable
fun ExerciseListScreen(navController: NavController, bodyPart: String) {
    val exerciseViewModel = LocalExerciseViewModel.current
    val exercises by exerciseViewModel.exercises.collectAsState()

    LaunchedEffect(bodyPart) {
        exerciseViewModel.loadExercises(bodyPart) // Load exercises for selected body part
    }

    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            ExerciseSearchBar()
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {

                exercises.forEach { exercise ->
                    Card(modifier = Modifier
                        .clickable {navController.navigate("exerciseDetail/${exercise.id}") }
                        .width(400.dp)) {
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) { Text(text = exercise.name, modifier = Modifier.fillMaxWidth()) }
                    }
                }
            }

        }
    }
}