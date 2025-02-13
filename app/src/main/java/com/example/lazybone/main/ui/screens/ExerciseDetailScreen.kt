package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.exerciseDetails.InputRaw
import com.example.lazybone.main.ui.components.exerciseDetails.Interaction
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar


@Composable
fun ExerciseDetailScreen(navController: NavController, exerciseId: String) {
    val exerciseViewModel = LocalExerciseViewModel.current
    val exercises by exerciseViewModel.exercises.collectAsState()
    val exercise = exercises.find { it.id == exerciseId }

    var weightInput by remember { mutableStateOf("") }
    var repsInput by remember { mutableStateOf("") }

    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(vertical = 20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (exercise != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = exercise.name, style = MaterialTheme.typography.headlineSmall
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 70.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val weight = weightInput.toDoubleOrNull() ?: 0.0
                    InputRaw(
                        label = "Weight (Kgs)",
                        value = weightInput,

                        onValueChange = { newValue ->
                            if (newValue.matches(Regex("^\\d{0,3}(\\.\\d?)?\$"))) {
                                weightInput = newValue
                            }
                        },
                        onIncrease = { weightInput = (weight + 2.5).toString() },
                        onDecrease = { if (weight > 0) weightInput = (weight - 2.5).toString() }
                    )

                    val reps = repsInput.toIntOrNull() ?: 0
                    InputRaw(
                        label = "Reps",
                        value = repsInput,
                        onValueChange = { newValue ->
                            if (newValue.matches(Regex("^\\d{0,2}$"))) {
                                repsInput = newValue
                            }
                        },
                        onIncrease = { repsInput = (reps + 1).toString() },
                        onDecrease = { if (reps > 0) repsInput = (reps - 1).toString() }
                    )
                    Interaction()
                }
                HorizontalDivider()
            }
        }
    }
}

