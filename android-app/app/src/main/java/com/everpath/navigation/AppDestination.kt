package com.everpath.navigation

sealed class AppDestination(
    val route: String
) {

    data object Everpath : AppDestination(
        route = "everpath"
    )

    data object GoalDetail : AppDestination(
        route = "goal_detail/{goalId}"
    ) {

        fun createRoute(
            goalId: String
        ): String {

            return "goal_detail/$goalId"

        }

    }

    object ActivityDetail : AppDestination(

        "activity_detail/{activityId}"

    ) {

        fun createRoute(
            activityId: String
        ): String {

            return "activity_detail/$activityId"

        }

    }

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