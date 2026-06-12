package com.everpath.navigation

sealed class AppDestination(
    val route: String
) {

    data object Everpath : AppDestination(
        route = "everpath"
    )
}
