package com.everpath.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.everpath.presentation.everpath.screen.EverpathScreen
import com.everpath.presentation.goaldetail.screen.GoalDetailScreen
import com.everpath.presentation.profile.screen.ProfileScreen
import com.everpath.presentation.quest.screen.QuestScreen
import com.everpath.presentation.today.screen.TodayScreen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination =
            AppDestination.Everpath.route
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
                AppDestination.Activities.route
        ) {

            TodayScreen()

        }

        composable(
            route =
                AppDestination.Quests.route
        ) {

            QuestScreen()

        }

        composable(
            route =
                AppDestination.Profile.route
        ) {

            ProfileScreen()

        }

    }

}