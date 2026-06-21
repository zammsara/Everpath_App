package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.goal.CompleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import kotlinx.coroutines.flow.first

/**
 * Orquestador encargado de completar
 * metas y evaluar achievements.
 */
class CompleteGoalWithAchievementsUseCase(

    private val completeGoalNodeUseCase:
    CompleteGoalNodeUseCase,

    private val getGoalNodesUseCase:
    GetGoalNodesUseCase,

    private val getUserProgressUseCase:
    GetUserProgressUseCase,

    private val getUserLevelUseCase:
    GetUserLevelUseCase,

    private val unlockAchievementsUseCase:
    UnlockAchievementsUseCase
) {

    suspend operator fun invoke(
        goal: GoalNode
    ) {

        completeGoalNodeUseCase(
            goal
        )

        val goals =
            getGoalNodesUseCase()
                .first()

        val xp =
            getUserProgressUseCase()
                .first()
                ?.xp ?: 0

        val level =
            getUserLevelUseCase(
                xp
            )

        unlockAchievementsUseCase(
            goals = goals,
            xp = xp,
            level = level
        )
    }
}