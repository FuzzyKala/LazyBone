package com.example.lazybone.main.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lazybone.main.ui.toolbars.MainTopBar

@Composable
fun FavoriteScreen(navController: NavController) {
    Scaffold(topBar = { MainTopBar(navController) }) { innerPadding ->
        Text(
            text = "Favorite Screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}