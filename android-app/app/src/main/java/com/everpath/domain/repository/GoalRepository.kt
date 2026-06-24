package com.everpath.domain.repository

import com.everpath.domain.model.GoalNode
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun getGoalNodes(): Flow<List<GoalNode>>

    fun getGoalNodeById(id: String): Flow<GoalNode?>

    suspend fun saveGoalNode(goalNode: GoalNode)

    suspend fun updateGoalNode(goalNode: GoalNode)

    suspend fun deleteGoalNode(id: String)
}