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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lazybone.R
import com.example.lazybone.main.ui.navigation.NavRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name)) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.icon_open_menu)
                )
            },
            actions = {
                IconButton(onClick = { navController.navigate(NavRoutes.RouteToExercise.route) }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.icon_add_exercise)
                    )
                }
                IconButton(onClick = { navController.navigate(NavRoutes.RouteToCalendar.route) }) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = stringResource(R.string.icon_calendar)
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = stringResource(R.string.icon_open_submenu)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(36.dp, 10.dp)
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.dropdown_setting)) },
                        onClick = { navController.navigate(NavRoutes.RouteToSetting.route) }
                    )
                }

            }

        )
    }
}