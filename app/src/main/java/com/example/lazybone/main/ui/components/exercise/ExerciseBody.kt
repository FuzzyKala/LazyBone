package com.example.lazybone.main.ui.components.exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.api.BodyPart
import com.example.lazybone.main.ui.navigation.NavRoutes

@Composable
fun ExerciseBody(
    navController: NavController,
    bodyParts: List<BodyPart>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        if (bodyParts.isNotEmpty()) {
            BodyPartCardTemplate(navController, NavRoutes.RouteToFavorite.route)
            bodyParts.forEach { bodyPart ->
                BodyPartCardTemplate(
                    navController = navController,
                    route = NavRoutes.routeToExerciseList(bodyPart.name),
                    bodyPart = bodyPart.name
                )
            }
        } else {
            Text("API calls fail, please check the network...")
        }

    }
}