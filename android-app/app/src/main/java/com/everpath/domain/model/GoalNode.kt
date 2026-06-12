package com.everpath.domain.model

import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType

data class GoalNode(
    val id: String,
    val title: String,
    val description: String,
    val lifeArea: LifeAreaType,
    val status: GoalStatus,
    val activities: List<Activity>
)