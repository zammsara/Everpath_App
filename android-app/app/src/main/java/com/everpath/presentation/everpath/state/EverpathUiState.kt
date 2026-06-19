package com.everpath.presentation.everpath.state

import com.everpath.domain.model.GoalConnection
import com.everpath.domain.model.GoalNode
import com.everpath.presentation.everpath.model.GoalNodePosition

data class EverpathUiState(
    val goalNodes: List<GoalNode> = emptyList(),
    val positions: List<GoalNodePosition> = emptyList(),
    val connections: List<GoalConnection> = emptyList(),

    val selectedGoalId: String? = null,
    val selectedConnectionId: String? = null,

    val connectionSourceGoalId: String? = null,
    val isConnectionMode: Boolean = false,

    val isLoading: Boolean = false,
    val errorMessage: String? = null
)