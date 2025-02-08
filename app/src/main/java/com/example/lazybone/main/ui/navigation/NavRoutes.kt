package com.example.lazybone.main.ui.navigation

import java.time.LocalDate

sealed class NavRoutes(val route: String) {

    data object Calendar : NavRoutes("calendar")
    data object Exercise : NavRoutes("exercise")
    data object Settings : NavRoutes("setting")

    companion object {
        fun mainRoute(date: LocalDate): String = "main/${date}"
    }
}
