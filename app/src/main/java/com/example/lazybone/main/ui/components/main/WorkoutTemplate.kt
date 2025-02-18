package com.example.lazybone.main.ui.components.main

import android.util.Log
import androidx.compose.foundation.clickable
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
                    .height(40.dp)
                    .padding(10.dp)
            ) {
                Text(text = workout.name, modifier = Modifier.padding(start = 2.dp))
                HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(top = 5.dp))
            }

            Column(modifier = Modifier.padding(top = 5.dp)) {
                run { Log.d("workouts", workouts.toString()) }
                workout.sets.forEach { workoutSet ->
                    Column {
                        Row {
                            Text(text = workoutSet.id.toString())
                        }
                        Row {
                            Text(text = workoutSet.weight.toString())
                        }
                        Row {
                            Text(text = workoutSet.reps.toString())
                        }
                    }
                }
            }
        }
    }


}