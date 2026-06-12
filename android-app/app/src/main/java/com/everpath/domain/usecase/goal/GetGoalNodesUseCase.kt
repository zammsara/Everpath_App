package com.everpath.domain.usecase.goal

import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GetGoalNodesUseCase(
    private val goalRepository: GoalRepository
) {

    operator fun invoke(): Flow<List<GoalNode>> {
        return goalRepository.getGoalNodes()
    }
}