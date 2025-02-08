package com.example.lazybone.main.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lazybone.main.ui.viewModel.DateViewModel
import java.time.format.DateTimeFormatter
import androidx.lifecycle.viewmodel.compose.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelector(viewModel: DateViewModel = viewModel()) {

    val selectedDate by viewModel.selectedDate // Get date from ViewModel
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd")

    Box(
        modifier = Modifier.drawBehind {
            drawLine(
                color = Color.Gray,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 2.dp.toPx()
            )
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(onClick = { viewModel.changeDate(-1) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "previous date"
                )
            }
            Text(selectedDate.format(formatter))
            IconButton(onClick = { viewModel.changeDate(1) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "next date"
                )
            }
        }
    }
}