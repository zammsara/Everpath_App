package com.everpath.presentation.everpath.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.presentation.everpath.viewmodel.EverpathViewModel

@Composable
fun EverpathScreen(
    viewModel: EverpathViewModel = viewModel()
) {

    val uiState = viewModel
        .uiState
        .collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "Goal Nodes: ${uiState.value.goalNodes.size}"
        )

    }
}