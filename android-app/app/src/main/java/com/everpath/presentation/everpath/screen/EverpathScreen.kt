package com.everpath.presentation.everpath.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.presentation.everpath.components.GoalNodeCard
import com.everpath.presentation.everpath.viewmodel.EverpathViewModel

@Composable
fun EverpathScreen(
    viewModel: EverpathViewModel = viewModel()
) {

    val uiState = viewModel
        .uiState
        .collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(uiState.value.goalNodes) { goalNode ->

            GoalNodeCard(
                goalNode = goalNode,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

        }

    }
}