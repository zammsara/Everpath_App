package com.everpath.domain.usecase.goal

import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository

/**
 * Caso de uso encargado de crear
 * una nueva meta en el backend.
 */
class SaveGoalNodeUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(
        goalNode: GoalNode,
        userId: Long
    ) {

        goalRepository.createGoal(
            goalNode,
            userId
        )

    }
}