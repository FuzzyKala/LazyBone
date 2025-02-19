package com.example.lazybone.main.ui.components.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.main.ui.navigation.NavRoutes

@Composable
fun CardTemplate(navController: NavController, iconText: String, description: String) {
    val icon = getIcon(iconText)

    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(NavRoutes.RouteToExercise.route)
            }
            .width(200.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = description)
            Text(description)
        }

    }
}

fun getIcon(iconText: String): ImageVector {
    return when (iconText) {
        "Add" -> Icons.Filled.Add
        "ContentCopy" -> Icons.Filled.ContentCopy
        else -> Icons.Filled.Cancel
    }
}