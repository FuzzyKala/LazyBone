package com.example.lazybone.main.ui.navigation

import android.os.Build
import android.util.Log
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
import com.example.lazybone.main.ui.screens.ExerciseDetailScreen
import com.example.lazybone.main.ui.screens.ExerciseListScreen
import com.example.lazybone.main.ui.screens.ExerciseScreen
import com.example.lazybone.main.ui.screens.FavoriteScreen
import com.example.lazybone.main.ui.screens.MainScreen
import com.example.lazybone.main.ui.screens.SettingScreen
import com.example.lazybone.main.ui.viewModel.DateViewModel
import com.example.lazybone.main.ui.viewModel.ExerciseViewModel
import com.example.lazybone.main.ui.viewModel.ProgramViewModel
import com.example.lazybone.main.ui.viewModel.WorkoutViewModel
import java.time.LocalDate

val LocalDateViewModel = compositionLocalOf<DateViewModel> { error("No DateViewModel provided") }
val LocalExerciseViewModel =
    compositionLocalOf<ExerciseViewModel> { error("No DateViewModel provided") }
val LocalWorkoutViewModel =
    compositionLocalOf<WorkoutViewModel> { error("No DateViewModel provided") }
val LocalWProgramViewModel =
    compositionLocalOf<ProgramViewModel> { error("No DateViewModel provided") }

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavController() {

    val navController = rememberNavController()
    val dateViewModel: DateViewModel = viewModel()

    val exerciseRepository = ExerciseRepository(RetrofitInstance.api)
    val exerciseViewModel: ExerciseViewModel = viewModel(
        factory = ExerciseViewModelFactory(exerciseRepository)
    )

    val workoutViewModel: WorkoutViewModel = viewModel()

    val programViewModel: ProgramViewModel = viewModel()

    CompositionLocalProvider(
        LocalDateViewModel provides dateViewModel,
        LocalExerciseViewModel provides exerciseViewModel,
        LocalWorkoutViewModel provides workoutViewModel,
        LocalWProgramViewModel provides programViewModel
    ) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.routeToMain(dateViewModel.today.value.toString())
        ) {
            composable(
                route = NavRoutes.routeToMain("{date}"),
                arguments = listOf(navArgument("date") {
                    defaultValue = dateViewModel.today.value.toString()
                }),
            ) { backStackEntry ->
                val dateString = backStackEntry.arguments?.getString("date")
                    ?: dateViewModel.today.value.toString()
                dateViewModel.setSelectedDate(LocalDate.parse(dateString))
                Log.d("dateString", dateString)
                MainScreen(navController)
            }

            composable(
                route = NavRoutes.RouteToExercise.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { ExerciseScreen(navController) }

            composable(
                route = NavRoutes.routeToExerciseList("{bodyPart}"),
                arguments = listOf(navArgument("bodyPart") { defaultValue = "back" }),
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { backStackEntry ->
                val bodyPart = backStackEntry.arguments?.getString("bodyPart") ?: "back"
                ExerciseListScreen(navController, bodyPart)
            }

            composable(
                route = NavRoutes.routeToExerciseDetail("{exerciseId}"),
                arguments = listOf(navArgument("exerciseId") { defaultValue = "" }),
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { backStackEntry ->
                val exerciseId = backStackEntry.arguments?.getString("exerciseId") ?: ""
                ExerciseDetailScreen(navController, exerciseId)
            }

            composable(
                route = NavRoutes.RouteToCalendar.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { CalendarScreen(navController) }

            composable(
                route = NavRoutes.RouteToSetting.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { SettingScreen(navController) }

            composable(
                route = NavRoutes.RouteToFavorite.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) { FavoriteScreen(navController) }
        }
    }

}