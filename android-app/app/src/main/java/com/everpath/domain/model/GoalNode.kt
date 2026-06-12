package com.everpath.domain.model

import com.everpath.domain.enums.GoalStatus

data class GoalNode(
    val id: String,
    val title: String,
    val description: String,
    val status: GoalStatus,
    val activities: List<Activity>
)