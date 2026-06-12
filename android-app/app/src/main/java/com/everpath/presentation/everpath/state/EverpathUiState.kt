package com.everpath.presentation.everpath.state

import com.everpath.domain.model.GoalNode

data class EverpathUiState(
    val goalNodes: List<GoalNode> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)