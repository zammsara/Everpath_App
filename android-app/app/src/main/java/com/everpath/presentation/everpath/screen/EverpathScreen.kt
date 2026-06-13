package com.everpath.presentation.everpath.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.EverpathApplication
import com.everpath.presentation.everpath.components.EverpathCanvas
import com.everpath.presentation.everpath.preview.GoalConnectionMockData
import com.everpath.presentation.everpath.preview.GoalNodePositionMockData
import com.everpath.presentation.everpath.viewmodel.EverpathViewModel
import com.everpath.presentation.everpath.viewmodel.EverpathViewModelFactory

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
                    .saveGoalNodeUseCase
        )

    }

    val viewModel: EverpathViewModel =
        viewModel(
            factory = factory
        )

    val uiState = viewModel
        .uiState
        .collectAsStateWithLifecycle()

    EverpathCanvas(
        goalNodes = uiState.value.goalNodes,
        positions = GoalNodePositionMockData.positions,
        connections = GoalConnectionMockData.connections,
        selectedGoalId = uiState.value.selectedGoalId,
        onGoalClick = { goalId ->
            viewModel.selectGoal(goalId)
        }
    )

}