package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.everpath.data.local.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO encargado de gestionar las operaciones de
 * persistencia para las actividades de una meta.
 */
@Dao
interface ActivityDao {

    @Query(
        "SELECT * FROM activities WHERE goalId = :goalId"
    )
    fun getActivitiesByGoalId(
        goalId: String
    ): Flow<List<ActivityEntity>>

    /**
     * Observa continuamente una actividad
     * almacenada en Room.
     *
     * Este método será utilizado por la capa
     * de presentación para implementar el
     * patrón Offline First, donde la UI
     * observa exclusivamente la base de datos
     * local.
     */
    @Query(
        "SELECT * FROM activities " +
                "WHERE id = :activityId " +
                "AND userId = :userId"
    )
    fun observeActivityById(
        activityId: String,
        userId: Long
    ): Flow<ActivityEntity?>

    @Query(
        "SELECT * FROM activities " +
                "WHERE id = :activityId " +
                "AND userId = :userId"
    )
    suspend fun getActivityById(
        activityId: String,
        userId: Long
    ): ActivityEntity?

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertActivity(
        activity: ActivityEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertActivities(
        activities: List<ActivityEntity>
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun upsertActivity(
        activity: ActivityEntity
    )

    @Update
    suspend fun updateActivity(
        activity: ActivityEntity
    )

    @Delete
    suspend fun deleteActivity(
        activity: ActivityEntity
    )

    @Query(
        "DELETE FROM activities WHERE id = :activityId"
    )
    suspend fun deleteActivityById(
        activityId: String
    )

    @Query(
        """
    DELETE FROM activities
    WHERE goalId = :goalId
    AND id NOT IN (:activityIds)
    """
    )
    suspend fun deleteActivitiesNotIn(
        goalId: String,
        activityIds: List<String>
    )
}