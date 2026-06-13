package com.everpath.data.repository

import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GoalRepositoryImpl(
    private val goalDao: GoalDao
) : GoalRepository {

    override fun getGoalNodes(): Flow<List<GoalNode>> {

        return goalDao
            .getAllGoals()
            .map { goals ->
                goals.map {
                    it.toDomain()
                }
            }
    }

    override suspend fun getGoalNodeById(
        id: String
    ): GoalNode? {

        return goalDao
            .getGoalById(id)
            ?.toDomain()
    }

    override suspend fun saveGoalNode(
        goalNode: GoalNode
    ) {

        goalDao.insertGoal(
            goalNode.toEntity()
        )
    }

    override suspend fun deleteGoalNode(
        id: String
    ) {

        val goal = goalDao
            .getGoalById(id)
            ?: return

        goalDao.deleteGoal(
            goal.goal
        )
    }
}