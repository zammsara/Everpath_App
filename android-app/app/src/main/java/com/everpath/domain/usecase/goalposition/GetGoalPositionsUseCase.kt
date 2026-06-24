package com.everpath.domain.usecase.goalposition

import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.repository.GoalPositionRepository
import kotlinx.coroutines.flow.Flow

class GetGoalPositionsUseCase(
    private val repository: GoalPositionRepository
) {

    operator fun invoke():
            Flow<List<GoalPositionEntity>> {

        return repository.getGoalPositions()
    }
}