package com.example.lazybone.main.ui.navigation

sealed class NavRoutes(val route: String) {
    data object Main : NavRoutes("main")
    data object Calendar : NavRoutes("calendar")
    data object Exercise : NavRoutes("exercise")
    data object Settings : NavRoutes("settings")
}
