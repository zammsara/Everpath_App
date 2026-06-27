package com.everpath.domain.usecase.goal

import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository

/**
 * Caso de uso encargado de actualizar
 * una meta existente.
 */
class UpdateGoalNodeUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(
        goalNode: GoalNode
    ) {

        goalRepository.updateGoal(
            goalNode
        )

    }
}