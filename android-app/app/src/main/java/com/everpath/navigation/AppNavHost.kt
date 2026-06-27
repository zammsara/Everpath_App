package com.everpath.navigation

import com.everpath.presentation.activitydetail.screen.ActivityDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.everpath.presentation.everpath.screen.EverpathScreen
import com.everpath.presentation.goaldetail.screen.GoalDetailScreen
import com.everpath.presentation.profile.screen.ProfileScreen
import com.everpath.presentation.today.screen.TodayScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination =
            AppDestination.Everpath.route,
        modifier = modifier
    ) {

        composable(
            route =
                AppDestination.Everpath.route
        ) {

            EverpathScreen(

                onGoalSelected = { goalId ->

                    navController.navigate(

                        AppDestination
                            .GoalDetail
                            .createRoute(goalId)

                    )

                }

            )

        }

        composable(

            route =
                AppDestination.GoalDetail.route,

            arguments = listOf(

                navArgument(
                    "goalId"
                ) {
                    type = NavType.StringType
                }

            )

        ) { backStackEntry ->

            val goalId =
                backStackEntry
                    .arguments
                    ?.getString("goalId")
                    ?: ""

            GoalDetailScreen(
                goalId = goalId,
                navController = navController
            )

        }

        composable(

            route =
                AppDestination.ActivityDetail.route,

            arguments = listOf(

                navArgument(
                    "activityId"
                ) {

                    type = NavType.StringType

                }

            )

        ) { backStackEntry ->

            val activityId =
                backStackEntry
                    .arguments
                    ?.getString(
                        "activityId"
                    )
                    ?: ""

            ActivityDetailScreen(

                activityId =
                    activityId,

                navController =
                    navController

            )

        }

        composable(
            route =
                AppDestination.Activities.route
        ) {

            TodayScreen()

        }

        composable(
            route =
                AppDestination.Profile.route
        ) {

            ProfileScreen()

        }

    }

}