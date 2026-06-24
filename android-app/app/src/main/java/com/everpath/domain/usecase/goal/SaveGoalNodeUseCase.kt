package com.everpath.domain.usecase.goal

import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository

class SaveGoalNodeUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(goalNode: GoalNode) {
        goalRepository.saveGoalNode(goalNode)
    }
}