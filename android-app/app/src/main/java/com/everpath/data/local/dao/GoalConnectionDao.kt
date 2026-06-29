package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everpath.data.local.entity.GoalConnectionEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO encargado de gestionar las conexiones
 * entre metas almacenadas en Room.
 */
@Dao
interface GoalConnectionDao {

    @Query(
        "SELECT * FROM goal_connections " +
                "WHERE userId = :userId"
    )
    fun getAllConnections(userId: Long):
            Flow<List<GoalConnectionEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertConnection(
        connection: GoalConnectionEntity
    )

    @Query(
        """
        DELETE FROM goal_connections
        WHERE id = :connectionId
        """
    )
    suspend fun deleteConnectionById(
        connectionId: String
    )
}