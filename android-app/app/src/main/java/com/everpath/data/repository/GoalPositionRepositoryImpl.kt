package com.everpath.data.repository

import com.everpath.data.local.dao.GoalPositionDao
import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.data.session.UserSession
import com.everpath.domain.repository.GoalPositionRepository
import kotlinx.coroutines.flow.Flow

class GoalPositionRepositoryImpl(
    private val goalPositionDao: GoalPositionDao
) : GoalPositionRepository {

    override fun getGoalPositions():
            Flow<List<GoalPositionEntity>> {

        return goalPositionDao.getAllPositions(
            UserSession.userId
        )
    }

    override suspend fun getGoalPositionById(
        goalId: String
    ): GoalPositionEntity? {

        return goalPositionDao
            .getPositionByGoalId(
                goalId = goalId,
                userId = UserSession.userId
            )
    }

    override suspend fun saveGoalPosition(
        position: GoalPositionEntity
    ) {

        goalPositionDao.savePosition(position)
    }

    override suspend fun updateGoalPosition(
        position: GoalPositionEntity
    ) {

        goalPositionDao.savePosition(
            position
        )

    }

    override suspend fun deleteGoalPosition(
        goalId: String
    ) {

        goalPositionDao.deletePosition(goalId)
    }
}