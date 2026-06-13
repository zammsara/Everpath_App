package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.everpath.data.local.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Query(
        "SELECT * FROM activities WHERE goalId = :goalId"
    )
    fun getActivitiesByGoalId(
        goalId: String
    ): Flow<List<ActivityEntity>>

    @Query(
        "SELECT * FROM activities WHERE id = :activityId"
    )
    suspend fun getActivityById(
        activityId: String
    ): ActivityEntity?

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertActivity(
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
}