package com.everpath.domain.usecase.goal

import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GetGoalNodeByIdUseCase(
    private val goalRepository: GoalRepository
) {
    operator fun invoke(
        id: String
    ): Flow<GoalNode?> {
        return goalRepository
            .getGoalNodeById(id)

    }

}