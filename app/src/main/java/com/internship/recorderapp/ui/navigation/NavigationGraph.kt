package com.internship.recorderapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.internship.recorderapp.ui.screens.main.MainScreen
import com.internship.recorderapp.vm.MainViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    val startDestination = NavRoutes.MAIN.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = NavRoutes.MAIN.route,
        ) {
            val vm = hiltViewModel<MainViewModel>()
            MainScreen(vm = vm)
        }
    }
}