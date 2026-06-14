package com.everpath.navigation

sealed class AppDestination(
    val route: String
) {

    data object Everpath : AppDestination(
        route = "everpath"
    )

    data object GoalDetail : AppDestination(
        route = "goal_detail"
    )

    data object Activities : AppDestination(
        route = "activities"
    )

    data object Quests : AppDestination(
        route = "quests"
    )

    data object Profile : AppDestination(
        route = "profile"
    )
}