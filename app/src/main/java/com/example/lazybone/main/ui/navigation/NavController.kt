package com.example.lazybone.main.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lazybone.main.ui.screens.CalendarScreen
import com.example.lazybone.main.ui.screens.ExerciseScreen
import com.example.lazybone.main.ui.screens.MainScreen
import com.example.lazybone.main.ui.screens.SettingsScreen


@Composable
fun AppNavController() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Main.route
    ) {
        composable(route = NavRoutes.Main.route) { MainScreen(navController) }
        composable(route = NavRoutes.Calendar.route) { CalendarScreen(navController) }
        composable(route = NavRoutes.Exercise.route) { ExerciseScreen(navController) }
        composable(route = NavRoutes.Settings.route) { SettingsScreen(navController) }
    }
}