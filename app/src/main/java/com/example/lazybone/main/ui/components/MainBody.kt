package com.example.lazybone.main.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lazybone.main.ui.navigation.LocalDateViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainBody(navController: NavController) {

    val dateViewModel = LocalDateViewModel.current
    val today = dateViewModel.today.value
    val selectedDate = dateViewModel.selectedDate.value
    Column {
        IconButton(onClick = { navController.navigate("exercise") }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add a new exercise")
        }
        Text("$today")
        Text("$selectedDate")
    }

}