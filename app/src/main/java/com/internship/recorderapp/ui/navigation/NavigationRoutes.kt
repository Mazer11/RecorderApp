package com.internship.recorderapp.ui.navigation

/**Navigation routes of [NavigationGraph].*/
sealed class NavRoutes(val route: String) {
    object MAIN : NavRoutes("main")
}