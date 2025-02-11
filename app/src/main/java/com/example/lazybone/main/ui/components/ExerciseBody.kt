package com.example.lazybone.main.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ExerciseBody(
    navController: NavController,
    bodyParts: List<String>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Card(modifier = Modifier
            .clickable {
//            navController.navigate("exercise")
            }
            .width(200.dp)) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { Text(text = "Favorites") }
        }

        if (bodyParts.isEmpty()) {
            Text(text = "No bodyParts found")
        } else {
            bodyParts.forEach { bodyPart ->
                Card(
                    modifier = Modifier
                        .clickable {
//            navController.navigate("exercise")
                        }
                        .width(200.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text(text = bodyPart) }
                }
            }
        }

    }

}