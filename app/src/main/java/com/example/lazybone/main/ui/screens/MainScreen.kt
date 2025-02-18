package com.example.lazybone.main.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController) {

    val programViewModel = LocalWProgramViewModel.current
    val dateViewModel = LocalDateViewModel.current

    val todayProgram = programViewModel.getProgramByDate(dateViewModel.today.value)
    Log.d("todayProgram", "$todayProgram")

    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            DateSelector(navController)
            MainBody(navController, todayProgram?.workouts ?: emptyList())
        }

    }
}