package com.everpath.domain.repository

import com.everpath.data.local.entity.GoalPositionEntity
import kotlinx.coroutines.flow.Flow

interface GoalPositionRepository {

    fun getGoalPositions():
            Flow<List<GoalPositionEntity>>

    suspend fun getGoalPositionById(
        goalId: String
    ): GoalPositionEntity?

    suspend fun saveGoalPosition(
        position: GoalPositionEntity
    )

    suspend fun deleteGoalPosition(
        goalId: String
    )
}