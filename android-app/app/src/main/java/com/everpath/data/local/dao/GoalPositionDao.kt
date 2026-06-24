package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everpath.data.local.entity.GoalPositionEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO encargado de persistir las posiciones visuales
 * de las metas dentro del mapa Everpath.
 */
@Dao
interface GoalPositionDao {

    @Query(
        "SELECT * FROM goal_positions"
    )
    fun getAllPositions():
            Flow<List<GoalPositionEntity>>

    @Query(
        "SELECT * FROM goal_positions WHERE goalId = :goalId"
    )
    suspend fun getPositionByGoalId(
        goalId: String
    ): GoalPositionEntity?

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun savePosition(
        position: GoalPositionEntity
    )

    @Query(
        "DELETE FROM goal_positions WHERE goalId = :goalId"
    )
    suspend fun deletePosition(
        goalId: String
    )
}
