package com.example.lazybone.main.ui.components.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.exerciseDetails.Workout

@Composable
fun WorkoutTemplate(navController: NavController, workouts: List<Workout>) {

    workouts.forEach { workout ->
        Card(
            modifier = Modifier
                .clickable {
                    navController.navigate("exercise")
                }
                .width(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = workout.name, modifier = Modifier.padding(start = 2.dp))
                HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(top = 5.dp))
            }

            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
                workout.sets.forEach { set ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = set.id.toString())
                            Text(text = "${set.weight} kg")
                            Text(text = "${set.reps} reps")
                        }
                    }
                }
            }
        }
    }


}