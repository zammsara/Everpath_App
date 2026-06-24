package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Activity
import com.everpath.domain.usecase.activity.CompleteActivityUseCase
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import kotlinx.coroutines.flow.first

/**
 * Orquestador encargado de completar
 * actividades y evaluar achievements.
 */
class CompleteActivityWithAchievementsUseCase(

    private val completeActivityUseCase:
    CompleteActivityUseCase,

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
        activity: Activity
    ) {

        completeActivityUseCase(
            activity
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