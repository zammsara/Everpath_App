package com.everpath.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.everpath.R
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
                    painter = painterResource(
                        id = R.drawable.ic_mapa
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
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
                    painter = painterResource(
                        id = R.drawable.ic_inicio
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            },

            label = {
                Text("Inicio")
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
                    painter = painterResource(
                        id = R.drawable.ic_perfil
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            },

            label = {
                Text("Perfil")
            }

        )

    }

}