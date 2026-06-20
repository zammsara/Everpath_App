package com.everpath.domain.usecase.goal

import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.userprogress.AddXpUseCase

/**
 * Caso de uso encargado de completar
 * una meta y otorgar experiencia.
 */
class CompleteGoalNodeUseCase(
    private val updateGoalNodeUseCase: UpdateGoalNodeUseCase,
    private val addXpUseCase: AddXpUseCase
) {

    suspend operator fun invoke(
        goal: GoalNode
    ) {

        if (
            goal.status ==
            GoalStatus.COMPLETED
        ) {
            return
        }

        updateGoalNodeUseCase(
            goal.copy(
                status =
                    GoalStatus.COMPLETED
            )
        )
        addXpUseCase(100)
    }
}