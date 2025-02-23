package com.example.lazybone.main.ui.components.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.R
import com.example.lazybone.main.ui.components.exerciseDetails.Workout


@Composable
fun MainBody(navController: NavController, workouts: List<Workout>) {

    Log.d("MainBody", "$workouts")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 10.dp)
    ) {
        if (workouts.isEmpty()) {
            CardTemplate(
                navController,
                stringResource(R.string.main_body_add_workout),
                stringResource(R.string.main_body_add_workout_desc)
            )
            CardTemplate(
                navController,
                stringResource(R.string.main_body_content_copy),
                stringResource(R.string.main_body_content_copy_desc)
            )
        } else {
            WorkoutTemplate(navController, workouts)
        }
    }

}