package com.everpath.data.repository

import com.everpath.data.local.dao.GoalPositionDao
import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.repository.GoalPositionRepository
import kotlinx.coroutines.flow.Flow

class GoalPositionRepositoryImpl(
    private val goalPositionDao: GoalPositionDao
) : GoalPositionRepository {

    override fun getGoalPositions():
            Flow<List<GoalPositionEntity>> {

        return goalPositionDao.getAllPositions()
    }

    override suspend fun getGoalPositionById(
        goalId: String
    ): GoalPositionEntity? {

        return goalPositionDao
            .getPositionByGoalId(goalId)
    }

    override suspend fun saveGoalPosition(
        position: GoalPositionEntity
    ) {

        goalPositionDao.savePosition(position)
    }

    override suspend fun deleteGoalPosition(
        goalId: String
    ) {

        goalPositionDao.deletePosition(goalId)
    }
}