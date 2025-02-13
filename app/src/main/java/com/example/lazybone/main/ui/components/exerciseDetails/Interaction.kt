package com.example.lazybone.main.ui.components.exerciseDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Interaction() {

    Row {
        Button(
            onClick = {/*Save*/ },
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 5.dp),
            shape = CutCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) { Text("Save") }
        Button(
            onClick = {/*Save*/ },
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 5.dp),
            shape = CutCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) { Text("Clear") }
    }
}