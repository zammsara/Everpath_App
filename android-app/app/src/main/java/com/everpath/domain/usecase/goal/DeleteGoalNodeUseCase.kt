package com.everpath.domain.usecase.goal

import com.everpath.domain.repository.GoalRepository

/**
 * Caso de uso encargado de eliminar
 * una meta.
 */
class DeleteGoalNodeUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(
        id: String
    ) {

        goalRepository.deleteGoal(
            id
        )

    }
}