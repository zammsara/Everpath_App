package com.everpath.domain.usecase.goalposition

import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.repository.GoalPositionRepository

class SaveGoalPositionUseCase(
    private val repository: GoalPositionRepository
) {

    suspend operator fun invoke(
        position: GoalPositionEntity
    ) {

        repository.saveGoalPosition(position)
    }
}