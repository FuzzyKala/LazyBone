package com.example.lazybone.main.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lazybone.main.ui.screens.CalendarScreen
import com.example.lazybone.main.ui.screens.ExerciseScreen
import com.example.lazybone.main.ui.screens.MainScreen
import com.example.lazybone.main.ui.screens.SettingsScreen
import com.example.lazybone.main.ui.viewModel.DateViewModel
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavController(dateViewModel: DateViewModel = viewModel()) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.mainRoute(LocalDate.now())
    ) {
        composable(
            route = "main/{date}",
            arguments = listOf(navArgument("date") { defaultValue = LocalDate.now().toString() }),
        ) { backStackEntry ->
            val dateString = backStackEntry.arguments?.getString("date")
            val selectedDate = LocalDate.parse(dateString)
            MainScreen(navController, selectedDate, dateViewModel)
        }


        composable(
            route = NavRoutes.Calendar.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) { CalendarScreen(navController) }

        composable(
            route = NavRoutes.Exercise.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) { ExerciseScreen(navController) }

        composable(
            route = NavRoutes.Settings.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
        ) { SettingsScreen(navController) }
    }
}