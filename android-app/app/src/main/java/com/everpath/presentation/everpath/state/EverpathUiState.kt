package com.everpath.presentation.everpath.state

import com.everpath.domain.model.GoalNode

data class EverpathUiState(
    val goalNodes: List<GoalNode> = emptyList(),
    val selectedGoalId: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)