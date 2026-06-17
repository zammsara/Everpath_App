package com.everpath.presentation.goaldetail.state

import com.everpath.domain.model.GoalNode

data class GoalDetailUiState(

    val goal: GoalNode? = null,

    val isLoading: Boolean = true

)