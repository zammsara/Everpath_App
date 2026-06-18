package com.everpath

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.everpath.navigation.AppNavHost
import com.everpath.presentation.components.BottomNavigationBar

@Composable
fun App() {

    val navController =
        rememberNavController()

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            Scaffold(

                bottomBar = {
                    BottomNavigationBar(
                        navController = navController
                    )
                }

            ) { paddingValues ->

                AppNavHost(

                    navController = navController,

                    modifier = Modifier.padding(
                        paddingValues
                    )

                )

            }

        }

    }

}