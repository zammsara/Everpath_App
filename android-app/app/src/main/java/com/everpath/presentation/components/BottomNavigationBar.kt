package com.everpath.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.everpath.navigation.AppDestination

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {

    val currentRoute =
        navController
            .currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route

    NavigationBar {

        NavigationBarItem(
            selected =
                currentRoute ==
                        AppDestination.Everpath.route,

            onClick = {

                navController.navigate(
                    AppDestination.Everpath.route
                )

            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = null
                )
            },

            label = {
                Text("Mapa")
            }

        )

        NavigationBarItem(
            selected =
                currentRoute ==
                        AppDestination.Activities.route,

            onClick = {

                navController.navigate(
                    AppDestination.Activities.route
                )

            },

            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = null
                )
            },

            label = {
                Text("Hoy")
            }

        )

        NavigationBarItem(
            selected =
                currentRoute ==
                        AppDestination.Quests.route,

            onClick = {

                navController.navigate(
                    AppDestination.Quests.route
                )

            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null
                )
            },

            label = {
                Text("Misiones")
            }

        )

        NavigationBarItem(
            selected =
                currentRoute ==
                        AppDestination.Profile.route,

            onClick = {

                navController.navigate(
                    AppDestination.Profile.route
                )

            },

            icon = {
                Icon(
                    imageVector =
                        Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },

            label = {
                Text("Perfil")
            }

        )

    }

}