package com.everpath.domain.usecase.goal

import com.everpath.domain.repository.GoalRepository

class DeleteGoalNodeUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(id: String) {
        goalRepository.deleteGoalNode(id)
    }
}