package com.everpath.presentation.goaldetail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.everpath.EverpathApplication
import com.everpath.presentation.everpath.components.EditGoalDialog
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModel
import com.everpath.presentation.goaldetail.viewmodel.GoalDetailViewModelFactory
import com.everpath.presentation.activity.viewmodel.ActivityViewModel
import com.everpath.presentation.activity.viewmodel.ActivityViewModelFactory

@Composable
fun GoalDetailScreen(
    goalId: String,
    navController: NavHostController
) {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {

        GoalDetailViewModelFactory(

            getGoalNodeByIdUseCase =
                application
                    .appContainer
                    .getGoalNodeByIdUseCase,

            updateGoalNodeUseCase =
                application
                    .appContainer
                    .updateGoalNodeUseCase,

            deleteGoalNodeUseCase =
                application
                    .appContainer
                    .deleteGoalNodeUseCase

        )

    }

    val activityFactory = remember {

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

    val uiState =
        viewModel.uiState
            .collectAsStateWithLifecycle()

    val activityUiState =
        activityViewModel
            .uiState
            .collectAsStateWithLifecycle()

    LaunchedEffect(goalId) {

        viewModel.loadGoal(
            goalId
        )

        activityViewModel.loadActivities(
            goalId
        )

    }

    if (uiState.value.goal == null) {

        CircularProgressIndicator()

        return

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = uiState.value.goal!!.title,
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Text(
            text = uiState.value.goal!!.description,
            modifier = Modifier.padding(
                top = 8.dp
            )
        )

        Text(
            text =
                "Área: ${uiState.value.goal!!.lifeArea}",
            modifier = Modifier.padding(
                top = 16.dp
            )
        )

        Text(
            text =
                "Estado: ${uiState.value.goal!!.status}"
        )

        Text(
            text =
                "Actividades: ${uiState.value.goal!!.activities.size}"
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

    if (
        showEditDialog.value &&
        uiState.value.goal != null
    ) {

        EditGoalDialog(

            initialTitle =
                uiState.value.goal!!.title,

            initialDescription =
                uiState.value.goal!!.description,

            onDismiss = {
                showEditDialog.value = false
            },

            onSave = { title, description ->

                viewModel.updateGoal(
                    title,
                    description
                )

                showEditDialog.value = false

            }

        )

    }

    if (showDeleteDialog.value) {

        AlertDialog(

            onDismissRequest = {
                showDeleteDialog.value = false
            },

            title = {
                Text("Eliminar Meta")
            },

            text = {
                Text(
                    "¿Deseas eliminar esta meta?"
                )
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

}