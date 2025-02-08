package com.example.lazybone.main.ui.toolbars

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TopAppBar(
            title = { Text("LazyBone") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Open Menu"
                )
            },
            actions = {
                IconButton(onClick = { navController.navigate("exercise") }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Exercise"
                    )
                }
                IconButton(onClick = { navController.navigate("calendar") }) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "Calendar"
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Open submenu"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(40.dp, 10.dp)
                ) {
                    DropdownMenuItem(
                        text = { Text("Setting") },
                        onClick = { navController.navigate("setting") }
                    )
                }

            }

        )
    }
}