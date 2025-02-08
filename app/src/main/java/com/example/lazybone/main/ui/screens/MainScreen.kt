package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lazybone.main.ui.toolbars.MainTopBar

@Composable
fun MainScreen(navController: NavController) {

    Scaffold(
        topBar = { MainTopBar(navController) }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Text(text = "Main Screen")
            IconButton(onClick = { navController.navigate("exercise") }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add a new exercise"
                )
            }
        }

    }
}