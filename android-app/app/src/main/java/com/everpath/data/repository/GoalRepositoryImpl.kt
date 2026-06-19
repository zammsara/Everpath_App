package com.everpath.data.repository

import com.everpath.data.local.dao.GoalDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación del repositorio de metas que conecta
 * la capa de dominio con Room mediante GoalDao.
 */
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

    override fun getGoalNodeById(
        id: String
    ): Flow<GoalNode?> {
        return goalDao
            .getGoalById(id)
            .map { goalWithActivities ->

                goalWithActivities?.toDomain()

            }

    }

    override suspend fun saveGoalNode(
        goalNode: GoalNode
    ) {
        goalDao.insertGoal(
            goalNode.toEntity()
        )
    }

    override suspend fun updateGoalNode(
        goalNode: GoalNode
    ) {
        goalDao.updateGoal(
            goalNode.toEntity()
        )

    }

    override suspend fun deleteGoalNode(
        id: String
    ) {
        goalDao.deleteGoalById(id)
    }
}