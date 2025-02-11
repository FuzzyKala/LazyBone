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
import com.example.lazybone.main.ui.components.ExerciseBody
import com.example.lazybone.main.ui.components.ExerciseSearchBar
import com.example.lazybone.main.ui.components.FavoriteList
import com.example.lazybone.main.ui.navigation.LocalExerciseViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar

@Composable
fun ExerciseScreen(
    navController: NavController
) {
    val exerciseViewModel = LocalExerciseViewModel.current
//    val exercises by exerciseViewModel.exercises.collectAsState()
    val bodyParts by exerciseViewModel.bodyParts.collectAsState()

    LaunchedEffect(Unit) {
//        exerciseViewModel.loadExercises("back")
        exerciseViewModel.loadBodyParts()
    }

    Scaffold(
        topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            ExerciseSearchBar()
            ExerciseBody(navController,bodyParts)
//            Card(modifier = Modifier
//                .clickable {
////            navController.navigate("exercise")
//                }
//                .width(200.dp)) { Text(text = "Favorites") }

//            if (bodyParts.isEmpty()) {
//                Text(text = "No bodyParts found")
//            } else {
//                bodyParts.forEach { bodyPart ->
//                    Card(
//                        modifier = Modifier
//                            .clickable {
////            navController.navigate("exercise")
//                            }
//                            .width(200.dp)
//                    ) { Text(text = bodyPart) }
//                }
//            }
        }

    }
}