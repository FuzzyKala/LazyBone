package com.example.lazybone.main.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lazybone.main.ui.components.DateSelector
import com.example.lazybone.main.ui.toolbars.MainTopBar
import com.example.lazybone.main.ui.viewModel.DateViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navController: NavController,
    selectedDate: LocalDate,
    dateViewModel: DateViewModel = viewModel()
) {

    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            DateSelector(selectedDate, navController, dateViewModel.today.value)

            IconButton(onClick = { navController.navigate("exercise") }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add a new exercise")
            }
        }

    }
}