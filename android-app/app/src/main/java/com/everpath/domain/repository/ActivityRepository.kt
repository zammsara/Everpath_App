package com.everpath.domain.repository

import com.everpath.domain.model.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    fun getActivitiesByGoalId(
        goalId: String
    ): Flow<List<Activity>>

    suspend fun getActivityById(
        activityId: String
    ): Activity?

    suspend fun saveActivity(
        activity: Activity
    )

    suspend fun updateActivity(
        activity: Activity
    )

    suspend fun deleteActivity(
        activityId: String
    )
}