package com.everpath.domain.usecase.goal

import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository

class GetGoalNodeByIdUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(id: String): GoalNode? {
        return goalRepository.getGoalNodeById(id)
    }
}