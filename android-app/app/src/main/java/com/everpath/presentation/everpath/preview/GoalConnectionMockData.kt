package com.everpath.presentation.everpath.preview

import com.everpath.domain.model.GoalConnection

object GoalConnectionMockData {

    val connections = listOf(

        GoalConnection(
            id = "connection_1",
            sourceGoalId = "goal_1",
            targetGoalId = "goal_2"
        ),

        GoalConnection(
            id = "connection_2",
            sourceGoalId = "goal_2",
            targetGoalId = "goal_3"
        )

    )
}