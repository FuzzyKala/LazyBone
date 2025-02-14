package com.example.lazybone.main.ui.navigation

sealed class NavRoutes(val route: String) {

    data object Calendar : NavRoutes("calendar")
    data object Exercise : NavRoutes("exercise")
    data object Settings : NavRoutes("setting")
    data object Favorites : NavRoutes("Favorite")

    companion object {
        fun mainRoute(date: String): String = "main/${date}"
    }
}
