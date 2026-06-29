package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.everpath.data.local.entity.GoalEntity
import com.everpath.data.local.relation.GoalWithActivities
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Transaction
    @Query("SELECT * FROM goal_nodes\n" +
            "WHERE userId = :userId")
    fun getAllGoals(userId: Long): Flow<List<GoalWithActivities>>

    @Transaction
    @Query(
        "SELECT * FROM goal_nodes " +
                "WHERE id = :goalId " +
                "AND userId = :userId"
    )
    fun getGoalById(goalId: String, userId: Long
    ): Flow<GoalWithActivities?>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertGoal(
        goal: GoalEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertGoals(
        goals: List<GoalEntity>
    )

    /**
     * Actualiza una meta existente.
     *
     * Devuelve la cantidad de filas modificadas.
     * Si devuelve 0 significa que la meta
     * aún no existe en Room.
     */
    @Update
    suspend fun updateGoal(
        goal: GoalEntity
    ): Int

    @Update
    suspend fun updateGoals(
        goals: List<GoalEntity>
    ): Int

    @Delete
    suspend fun deleteGoal(
        goal: GoalEntity
    )

    @Query(
        "DELETE FROM goal_nodes WHERE id = :goalId"
    )
    suspend fun deleteGoalById(
        goalId: String
    )

}