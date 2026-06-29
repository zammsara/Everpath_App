package com.everpath.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.di.AppContainer
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
import com.everpath.presentation.login.screen.LoginScreen
import com.everpath.presentation.register.screen.RegisterScreen
import com.everpath.presentation.splash.screen.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    appContainer: AppContainer,
    modifier: Modifier = Modifier
) {
    NavHost(

        navController = navController,

        startDestination =
            AppDestination.Splash.route,

        modifier = modifier
    ) {

        composable(
            route =
                AppDestination.Splash.route
        ) {

            SplashScreen(

                viewModel = viewModel(
                    factory =
                        appContainer
                            .splashViewModelFactory

                ),

                onSessionRestored = {

                    navController.navigate(
                        AppDestination.Everpath.route
                    ) {
                        popUpTo(
                            AppDestination.Splash.route
                        ) {
                            inclusive = true
                        }
                    }
                },

                onSessionNotFound = {

                    navController.navigate(
                        AppDestination.Login.route
                    ) {

                        popUpTo(
                            AppDestination.Splash.route
                        ) {
                            inclusive = true
                        }

                    }

                }

            )

        }

        composable(
            route =
                AppDestination.Login.route
        ) {

            LoginScreen(

                viewModel = viewModel(
                    factory =
                        appContainer
                            .loginViewModelFactory
                ),

                onLoginSuccess = {

                    navController.navigate(
                        AppDestination.Everpath.route
                    ) {

                        popUpTo(
                            AppDestination.Login.route
                        ) {
                            inclusive = true
                        }

                    }

                },

                onNavigateToRegister = {

                    navController.navigate(
                        AppDestination.Register.route
                    )

                }

            )

        }

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

        composable(
            route =
                AppDestination.Register.route
        ) {

            RegisterScreen(

                viewModel = viewModel(

                    factory =
                        appContainer
                            .registerViewModelFactory

                ),

                onRegisterSuccess = {

                    navController.navigate(
                        AppDestination.Everpath.route
                    ) {

                        popUpTo(
                            AppDestination.Login.route
                        ) {
                            inclusive = true
                        }

                    }

                },

                onNavigateToLogin = {

                    navController.popBackStack()
                }
            )
        }


    }


}