package com.example.lazybone.main.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lazybone.main.api.ExerciseRepository
import com.example.lazybone.main.api.ExerciseViewModelFactory
import com.example.lazybone.main.api.RetrofitInstance
import com.example.lazybone.main.ui.screens.CalendarScreen
import com.example.lazybone.main.ui.screens.ExerciseScreen
import com.example.lazybone.main.ui.screens.MainScreen
import com.example.lazybone.main.ui.screens.SettingsScreen
import com.example.lazybone.main.ui.viewModel.DateViewModel
import com.example.lazybone.main.ui.viewModel.ExerciseViewModel
import java.time.LocalDate

val LocalDateViewModel = compositionLocalOf<DateViewModel> { error("No DateViewModel provided") }
val LocalExerciseViewModel =
    compositionLocalOf<ExerciseViewModel> { error("No DateViewModel provided") }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavController() {

    val navController = rememberNavController()
    val dateViewModel: DateViewModel = viewModel()


    val exerciseRepository = ExerciseRepository(RetrofitInstance.api)

    val exerciseViewModel: ExerciseViewModel = viewModel(
        factory = ExerciseViewModelFactory(exerciseRepository)
    )

    CompositionLocalProvider(
        LocalDateViewModel provides dateViewModel,
        LocalExerciseViewModel provides exerciseViewModel
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.mainRoute(dateViewModel.today.value.toString())
        ) {
            composable(
                route = "main/{date}",
                arguments = listOf(navArgument("date") {
                    defaultValue = dateViewModel.today.value.toString()
                }),
            ) { backStackEntry ->
                val dateString = backStackEntry.arguments?.getString("date")
                    ?: dateViewModel.today.value.toString()

                dateViewModel.setSelectedDate(LocalDate.parse(dateString))
                MainScreen(navController)
            }

            composable(
                route = NavRoutes.Exercise.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { ExerciseScreen(navController) }


//            composable(
//                route = "exercise/bodyPart/{bodyPart}",
//                argument = listOf(navArgument())
//            ) { ExerciseScreen(navController) }

            composable(
                route = NavRoutes.Calendar.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { CalendarScreen(navController) }



            composable(
                route = NavRoutes.Settings.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { SettingsScreen(navController) }
        }
    }

}