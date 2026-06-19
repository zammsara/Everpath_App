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
import androidx.compose.material.icons.filled.Share
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Visibility
import com.everpath.presentation.everpath.components.EditGoalDialog

/**
 * Pantalla principal del mapa Everpath.
 *
 * Actúa como punto de entrada de la funcionalidad central
 * de la aplicación y conecta la interfaz gráfica con el
 * EverpathViewModel.
 */
@Composable
fun EverpathScreen(
    onGoalSelected: (String) -> Unit
) {

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
            updateGoalPositionUseCase =
                application
                    .appContainer
                    .updateGoalPositionUseCase,
            getGoalPositionsUseCase =
                application
                    .appContainer
                    .getGoalPositionsUseCase,
            deleteGoalNodeUseCase =
                application
                    .appContainer
                    .deleteGoalNodeUseCase,
            getGoalConnectionsUseCase =
                application
                    .appContainer
                    .getGoalConnectionsUseCase,

            saveGoalConnectionUseCase =
                application
                    .appContainer
                    .saveGoalConnectionUseCase,

            deleteGoalConnectionUseCase =
                application
                    .appContainer
                    .deleteGoalConnectionUseCase
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
            Column(
                modifier = Modifier.padding(
                    bottom = 80.dp
                )
            ) {
                if (
                    uiState.value.selectedConnectionId
                    != null
                ) {
                    FloatingActionButton(
                        onClick = {
                            viewModel
                                .deleteSelectedConnection()
                        }
                    ) {
                        Icon(
                            imageVector =
                                Icons.Default.Delete,
                            contentDescription =
                                "Eliminar Conexión"
                        )
                    }
                }

                if (
                    uiState.value.selectedGoalId != null
                ) {

                    FloatingActionButton(
                        onClick = {

                            uiState.value.selectedGoalId
                                ?.let { goalId ->

                                    onGoalSelected(goalId)

                                }

                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Ver Detalles"
                        )

                    }

                    FloatingActionButton(
                        onClick = {
                            showEditGoalDialog.value = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar Meta"
                        )
                    }

                    FloatingActionButton(
                        onClick = {
                            viewModel.startConnectionMode()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Conectar Metas"
                        )

                    }

                    FloatingActionButton(
                        onClick = {
                            viewModel.deleteSelectedGoal()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar Meta"
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
                        contentDescription = "Crear Meta"
                    )
                }
            }
        }

    ) { paddingValues ->

        Column {

            if (
                uiState.value.isConnectionMode
            ) {

                Text(

                    text =
                        "Selecciona la meta destino para crear la conexión",

                    modifier =
                        Modifier.padding(
                            16.dp
                        )

                )

            }

            EverpathCanvas(
                goalNodes = uiState.value.goalNodes,
                positions = uiState.value.positions,
                connections = uiState.value.connections,
                draggingPositions = uiState.value.draggingPositions,
                selectedGoalId = uiState.value.selectedGoalId,
                selectedConnectionId = uiState.value.selectedConnectionId,
                onConnectionClick = { connectionId ->
                    viewModel.selectConnection(connectionId)
                },
                onGoalClick = { goalId ->
                    viewModel.handleGoalSelection(goalId)
                },
                onDragStart = { goalId ->
                    viewModel.startDragging(goalId)
                },
                onDrag = { goalId, dragX, dragY ->
                    viewModel.dragGoal(
                        goalId,
                        dragX,
                        dragY
                    )
                },
                onDragEnd = { goalId ->
                    viewModel.finishDragging(goalId)
                },
                onBackgroundClick = {
                    viewModel.clearSelection()
                },
                modifier =
                    Modifier.padding(paddingValues)
            )
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
            initialStatus =
                selectedGoal.status,
            onDismiss = {
                showEditGoalDialog.value = false
            },
            onSave = {
                    title,
                    description,
                    status ->
                viewModel.updateGoal(
                    title = title,
                    description = description,
                    status = status

                )
                showEditGoalDialog.value =
                    false
            }

        )

    }

}
}