package com.everpath.presentation.everpath.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.EverpathApplication
import com.everpath.presentation.everpath.components.EverpathCanvas
import com.everpath.presentation.everpath.viewmodel.EverpathViewModel
import com.everpath.presentation.everpath.viewmodel.EverpathViewModelFactory
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Modifier
import com.everpath.presentation.everpath.components.CreateGoalDialog
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.filled.Edit
import com.everpath.presentation.everpath.components.EditGoalDialog
import com.everpath.presentation.everpath.components.GoalDetailsCard

@Composable
fun EverpathScreen() {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {

        EverpathViewModelFactory(
            getGoalNodesUseCase =
                application
                    .appContainer
                    .getGoalNodesUseCase,

            saveGoalNodeUseCase =
                application
                    .appContainer
                    .saveGoalNodeUseCase,

            updateGoalNodeUseCase =
                application
                    .appContainer
                    .updateGoalNodeUseCase,

            saveGoalPositionUseCase =
                application
                    .appContainer
                    .saveGoalPositionUseCase,

            getGoalPositionsUseCase =
                application
                    .appContainer
                    .getGoalPositionsUseCase,

            deleteGoalNodeUseCase =
                application
                    .appContainer
                    .deleteGoalNodeUseCase
        )

    }

    val viewModel: EverpathViewModel =
        viewModel(
            factory = factory
        )

    val uiState = viewModel
        .uiState
        .collectAsStateWithLifecycle()

    val selectedGoal =
        uiState.value.goalNodes.find {

            it.id ==
                    uiState.value.selectedGoalId

        }

    val showCreateGoalDialog =
        remember {
            mutableStateOf(false)
        }

    val showEditGoalDialog =
        remember {
            mutableStateOf(false)
        }

    Scaffold(

        floatingActionButton = {

            Column {

                if (
                    uiState.value.selectedGoalId != null
                ) {

                    FloatingActionButton(
                        onClick = {
                            showEditGoalDialog.value = true
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar Goal"
                        )

                    }

                    FloatingActionButton(
                        onClick = {
                            viewModel.deleteSelectedGoal()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar Goal"
                        )

                    }

                }

                FloatingActionButton(
                    onClick = {
                        showCreateGoalDialog.value = true
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Crear Goal"
                    )

                }

            }

        }

    ) { paddingValues ->

        Column {

            EverpathCanvas(
                goalNodes = uiState.value.goalNodes,
                positions = uiState.value.positions,
                connections = uiState.value.connections,
                selectedGoalId = uiState.value.selectedGoalId,
                onGoalClick = { goalId ->
                    viewModel.selectGoal(goalId)
                },
                modifier = Modifier.padding(
                    paddingValues
                )
            )

            if (selectedGoal != null) {

                GoalDetailsCard(
                    goalNode = selectedGoal
                )

            }

        }

    }

    if (showCreateGoalDialog.value) {

        CreateGoalDialog(

            onDismiss = {
                showCreateGoalDialog.value = false
            },

            onSave = { title, description ->

                viewModel.createGoal(
                    title = title,
                    description = description
                )

                showCreateGoalDialog.value = false

            }

        )

    }

    if (
        showEditGoalDialog.value &&
        selectedGoal != null
    ) {

        EditGoalDialog(

            initialTitle =
                selectedGoal.title,

            initialDescription =
                selectedGoal.description,

            onDismiss = {
                showEditGoalDialog.value = false
            },

            onSave = { title, description ->

                viewModel.updateGoal(
                    title = title,
                    description = description
                )

                showEditGoalDialog.value = false

            }

        )

    }

}