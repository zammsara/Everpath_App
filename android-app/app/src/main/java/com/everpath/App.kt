package com.everpath

import androidx.compose.ui.platform.LocalContext
import com.everpath.di.AppContainer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.everpath.navigation.AppDestination
import com.everpath.navigation.AppNavHost
import com.everpath.presentation.components.BottomNavigationBar


@Composable
fun App() {

    val navController =
        rememberNavController()

    val appContainer =
        (LocalContext.current.applicationContext
                as EverpathApplication)
            .appContainer

    val currentBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        currentBackStackEntry
            ?.destination
            ?.route
    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            Scaffold(

                bottomBar = {

                    val showBottomBar =

                        currentRoute != null &&

                                currentRoute !=
                                AppDestination.Splash.route &&

                                currentRoute !=
                                AppDestination.Login.route &&

                                currentRoute !=
                                AppDestination.Register.route

                    if (showBottomBar) {

                        BottomNavigationBar(
                            navController = navController
                        )

                    }

                }

            ) { paddingValues ->

                AppNavHost(

                    navController = navController,
                    appContainer = appContainer,
                    modifier = Modifier.padding(paddingValues)

                )

            }

        }

    }

}