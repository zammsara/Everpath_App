package com.everpath.domain.usecase.goalposition

import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.repository.GoalPositionRepository

class GetGoalPositionByIdUseCase(
    private val repository: GoalPositionRepository
) {

    suspend operator fun invoke(
        goalId: String
    ): GoalPositionEntity? {

        return repository
            .getGoalPositionById(goalId)
    }
}