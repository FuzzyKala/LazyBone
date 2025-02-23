package com.example.lazybone.main.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.main.DateSelector
import com.example.lazybone.main.ui.components.main.MainBody
import com.example.lazybone.main.ui.navigation.LocalDateViewModel
import com.example.lazybone.main.ui.navigation.LocalWProgramViewModel
import com.example.lazybone.main.ui.toolbars.MainTopBar

@Composable
fun MainScreen(navController: NavController) {

    val programViewModel = LocalWProgramViewModel.current
    val dateViewModel = LocalDateViewModel.current

    val today = dateViewModel.today.value
    val selectedDate = dateViewModel.selectedDate.value

    val renderingProgram = programViewModel.getProgramByDate(selectedDate)

    Log.d("todayProgram", "$renderingProgram")
    Log.d("todayProgram", "today:$today")
    Log.d("todayProgram", "selectedDate:$selectedDate")

    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            DateSelector(navController, today, selectedDate)
            MainBody(navController, renderingProgram?.workouts ?: emptyList())
        }

    }
}