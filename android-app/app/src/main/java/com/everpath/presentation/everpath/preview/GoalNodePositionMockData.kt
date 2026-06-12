package com.everpath.presentation.everpath.preview

import com.everpath.presentation.everpath.model.GoalNodePosition

object GoalNodePositionMockData {

    val positions = listOf(

        GoalNodePosition(
            goalNodeId = "goal_1",
            x = 100f,
            y = 100f
        ),

        GoalNodePosition(
            goalNodeId = "goal_2",
            x = 350f,
            y = 250f
        ),

        GoalNodePosition(
            goalNodeId = "goal_3",
            x = 600f,
            y = 400f
        )

    )
}