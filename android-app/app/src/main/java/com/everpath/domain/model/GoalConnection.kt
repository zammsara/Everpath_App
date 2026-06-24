package com.everpath.domain.model

data class GoalConnection(
    val id: String,
    val sourceGoalId: String,
    val targetGoalId: String
)