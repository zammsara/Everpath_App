package com.everpath.domain.usecase.goalposition

import com.everpath.domain.repository.GoalPositionRepository

class DeleteGoalPositionUseCase(
    private val repository: GoalPositionRepository
) {

    suspend operator fun invoke(
        goalId: String
    ) {

        repository.deleteGoalPosition(goalId)
    }
}