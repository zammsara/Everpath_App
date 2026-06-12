package com.everpath.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.everpath.presentation.everpath.screen.EverpathScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Everpath.route
    ) {

        composable(
            route = AppDestination.Everpath.route
        ) {
            EverpathScreen()
        }
    }
}