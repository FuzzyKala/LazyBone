package com.example.lazybone.main.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FavoriteList(navController: NavController) {
    Card(modifier = Modifier
        .clickable {
//            navController.navigate("exercise")
        }
        .width(200.dp)) { Text(text = "Favorites") }
}