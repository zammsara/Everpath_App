package com.everpath.domain.usecase.goal

import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.GoalNode

class CalculateGoalProgressUseCase {

    operator fun invoke(goalNode: GoalNode): Float {

        if (goalNode.activities.isEmpty()) {
            return 0f
        }

        val completedActivities = goalNode.activities.count {
            it.status == ActivityStatus.COMPLETED
        }

        return completedActivities.toFloat() /
                goalNode.activities.size.toFloat()
    }
}