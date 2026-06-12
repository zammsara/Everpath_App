package com.everpath.presentation.everpath.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.presentation.everpath.components.EverpathCanvas
import com.everpath.presentation.everpath.preview.GoalConnectionMockData
import com.everpath.presentation.everpath.preview.GoalNodePositionMockData
import com.everpath.presentation.everpath.viewmodel.EverpathViewModel

@Composable
fun EverpathScreen(
    viewModel: EverpathViewModel = viewModel()
) {

    val uiState = viewModel
        .uiState
        .collectAsStateWithLifecycle()

    EverpathCanvas(
        goalNodes = uiState.value.goalNodes,
        positions = GoalNodePositionMockData.positions,
        connections = GoalConnectionMockData.connections
    )
}