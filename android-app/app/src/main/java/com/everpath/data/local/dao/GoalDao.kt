package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.everpath.data.local.entity.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal_nodes")
    fun getAllGoals(): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goal_nodes WHERE id = :goalId")
    suspend fun getGoalById(
        goalId: String
    ): GoalEntity?

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertGoal(
        goal: GoalEntity
    )

    @Update
    suspend fun updateGoal(
        goal: GoalEntity
    )

    @Delete
    suspend fun deleteGoal(
        goal: GoalEntity
    )
}