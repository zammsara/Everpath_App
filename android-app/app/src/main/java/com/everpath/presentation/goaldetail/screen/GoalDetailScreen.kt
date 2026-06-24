package com.everpath.presentation.goaldetail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.everpath.EverpathApplication
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.enums.GoalStatus
import com.everpath.navigation.AppDestination
import com.everpath.presentation.activity.components.ActivityList
import com.everpath.presentation.activity.components.CreateActivityDialog
import com.everpath.presentation.activity.viewmodel.ActivityViewModel
import com.everpath.presentation.activity.viewmodel.ActivityViewModelFactory
import com.everpath.presentation.everpath.components.EditGoalDialog
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModel
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModelFactory


@Composable
fun GoalDetailScreen(
    goalId: String,
    navController: NavHostController
) {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory =
        remember {

            GoalDetailViewModelFactory(
                getGoalNodeByIdUseCase =
                    application
                        .appContainer
                        .getGoalNodeByIdUseCase,

                updateGoalNodeUseCase =
                    application
                        .appContainer
                        .updateGoalNodeUseCase,

                completeGoalWithAchievementsUseCase =
                    application
                        .appContainer
                        .completeGoalWithAchievementsUseCase,

                deleteGoalNodeUseCase =
                    application
                        .appContainer
                        .deleteGoalNodeUseCase
            )

        }

    val activityFactory =
        remember {

            ActivityViewModelFactory(
                getActivitiesByGoalIdUseCase =
                    application
                        .appContainer
                        .getActivitiesByGoalIdUseCase,

                saveActivityUseCase =
                    application
                        .appContainer
                        .saveActivityUseCase,

                updateActivityUseCase =
                    application
                        .appContainer
                        .updateActivityUseCase,

                deleteActivityUseCase =
                    application
                        .appContainer
                        .deleteActivityUseCase
            )

        }

    val viewModel: GoalDetailViewModel =
        viewModel(
            factory = factory
        )

    val activityViewModel: ActivityViewModel =
        viewModel(
            factory = activityFactory
        )

    val showEditDialog =
        remember {
            mutableStateOf(false)
        }

    val showDeleteDialog =
        remember {
            mutableStateOf(false)
        }

    val showCreateActivityDialog =
        remember {
            mutableStateOf(false)
        }

    val uiState =
        viewModel
            .uiState
            .collectAsStateWithLifecycle()

    val activityUiState =
        activityViewModel
            .uiState
            .collectAsStateWithLifecycle()

    LaunchedEffect(
        goalId
    ) {

        viewModel.loadGoal(
            goalId
        )

        activityViewModel.loadActivities(
            goalId
        )

    }

    LaunchedEffect(
        activityUiState.value.activities
    ) {

        val activities =
            activityUiState
                .value
                .activities

        if (
            activities.isNotEmpty()
        ) {

            val allCompleted =
                activities.all {
                    it.status == ActivityStatus.COMPLETED
                }

            if (
                allCompleted &&
                uiState.value.goal?.status != GoalStatus.COMPLETED
            ) {

                viewModel.updateGoalStatus(
                    GoalStatus.COMPLETED
                )

            } else if (
                !allCompleted &&
                uiState.value.goal?.status == GoalStatus.COMPLETED
            ) {

                viewModel.updateGoalStatus(
                    GoalStatus.ACTIVE
                )

            }

        }

    }

    val currentGoal =
        uiState.value.goal

    if (
        currentGoal == null
    ) {

        CircularProgressIndicator()

        return

    }

    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    showCreateActivityDialog.value = true
                }
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Actividad"
                )

            }

        }
    ) { paddingValues ->

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
        ) {

            val totalActivities =
                activityUiState.value.activities.size

            val completedActivities =
                activityUiState.value.activities.count {
                    it.status.name == "COMPLETED"
                }

            val progressPercentage =
                if (
                    totalActivities == 0
                ) {
                    0
                } else {
                    (completedActivities * 100) / totalActivities
                }

            Text(
                text = currentGoal.title,
                style =
                    MaterialTheme
                        .typography
                        .headlineMedium
            )

            Text(
                text = currentGoal.description,
                modifier =
                    Modifier.padding(
                        top = 8.dp
                    )
            )

            Text(
                text = "Área: ${currentGoal.lifeArea}",
                modifier =
                    Modifier.padding(
                        top = 16.dp
                    )
            )

            Text(
                text =
                    when (
                        currentGoal.status
                    ) {
                        GoalStatus.ACTIVE ->
                            "Estado: Activa"

                        GoalStatus.COMPLETED ->
                            "Estado: Completada"

                        GoalStatus.LOCKED ->
                            "Estado: Bloqueada"

                        GoalStatus.ARCHIVED ->
                            "Estado: Archivada"
                    },
                color =
                    when (
                        currentGoal.status
                    ) {
                        GoalStatus.ACTIVE ->
                            androidx.compose.ui.graphics.Color.Blue

                        GoalStatus.COMPLETED ->
                            androidx.compose.ui.graphics.Color(0xFF4CAF50)

                        GoalStatus.LOCKED ->
                            androidx.compose.ui.graphics.Color.Gray

                        GoalStatus.ARCHIVED ->
                            androidx.compose.ui.graphics.Color.DarkGray
                    },
                modifier =
                    Modifier.padding(
                        top = 8.dp
                    )
            )

            Text(
                text =
                    "Progreso: $completedActivities/$totalActivities actividades ($progressPercentage%)",
                modifier =
                    Modifier.padding(
                        top = 12.dp
                    )
            )

            LinearProgressIndicator(
                progress = {
                    activityUiState
                        .value
                        .progress
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp
                        )
            )

            Text(
                text = "Actividades",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge,
                modifier =
                    Modifier.padding(
                        top = 16.dp
                    )
            )

            ActivityList(
                activities =
                    activityUiState
                        .value
                        .activities,

                onActivityClick = { activityId ->

                    navController.navigate(
                        AppDestination
                            .ActivityDetail
                            .createRoute(
                                activityId
                            )
                    )

                }
            )

            Button(
                onClick = {
                    showEditDialog.value = true
                }
            ) {

                Text("Editar")

            }

            Button(
                onClick = {
                    showDeleteDialog.value = true
                }
            ) {

                Text("Eliminar")

            }

        }

    }

    if (
        showEditDialog.value
    ) {

        EditGoalDialog(
            initialTitle =
                currentGoal.title,

            initialDescription =
                currentGoal.description,

            initialStatus =
                currentGoal.status,

            initialLifeArea =
                currentGoal.lifeArea,

            onDismiss = {
                showEditDialog.value = false
            },

            onSave = {
                    title,
                    description,
                    status,
                    lifeArea ->

                viewModel.updateGoal(
                    title = title,
                    description = description,
                    status = status,
                    lifeArea = lifeArea
                )

                showEditDialog.value = false

            }
        )

    }

    if (
        showDeleteDialog.value
    ) {

        AlertDialog(
            onDismissRequest = {
                showDeleteDialog.value = false
            },

            title = {
                Text("Eliminar Meta")
            },

            text = {
                Text("¿Deseas eliminar esta meta?")
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        viewModel.deleteGoal {
                            navController.popBackStack()
                        }

                    }
                ) {

                    Text("Eliminar")

                }

            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showDeleteDialog.value = false
                    }
                ) {

                    Text("Cancelar")

                }

            }
        )

    }

    if (
        showCreateActivityDialog.value
    ) {

        CreateActivityDialog(
            onDismiss = {
                showCreateActivityDialog.value = false
            },

            onSave = { title, description ->

                activityViewModel.createActivity(
                    goalId = currentGoal.id,
                    title = title,
                    description = description
                )

                showCreateActivityDialog.value = false

            }
        )

    }

}