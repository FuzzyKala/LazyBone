package com.example.lazybone.main.ui.navigation

sealed class NavRoutes(val route: String) {

    data object RouteToCalendar : NavRoutes("calendar")
    data object RouteToExercise : NavRoutes("exercise")
    data object RouteToSetting : NavRoutes("setting")
    data object RouteToFavorite : NavRoutes("favorite")

    companion object {
        fun routeToMain(date: String): String = "main/$date"
        fun routeToExerciseList(bodyPart: String): String = "exercise/bodyPart/$bodyPart"
        fun routeToExerciseDetail(exerciseId: String): String = "exerciseDetail/$exerciseId"
    }
}
