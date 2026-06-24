package com.everpath.domain.usecase.goalposition

import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.repository.GoalPositionRepository

/**
 * Caso de uso encargado de actualizar
 * la posición visual de una meta.
 */
class UpdateGoalPositionUseCase(
    private val repository: GoalPositionRepository
) {

    suspend operator fun invoke(
        position: GoalPositionEntity
    ) {

        repository.updateGoalPosition(
            position
        )

    }

}