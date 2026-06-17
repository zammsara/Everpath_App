package com.everpath.data.repository

import com.everpath.data.local.dao.ActivityDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ActivityRepositoryImpl(
    private val activityDao: ActivityDao
) : ActivityRepository {

    override fun getActivitiesByGoalId(
        goalId: String
    ): Flow<List<Activity>> {

        return activityDao
            .getActivitiesByGoalId(goalId)
            .map { activities ->

                activities.map {
                    it.toDomain()
                }

            }
    }

    override suspend fun getActivityById(
        activityId: String
    ): Activity? {

        return activityDao
            .getActivityById(activityId)
            ?.toDomain()
    }

    override suspend fun saveActivity(
        activity: Activity
    ) {

        activityDao.insertActivity(
            activity.toEntity()
        )
    }

    override suspend fun updateActivity(
        activity: Activity
    ) {

        activityDao.updateActivity(
            activity.toEntity()
        )
    }

    override suspend fun deleteActivity(
        activityId: String
    ) {

        activityDao.deleteActivityById(
            activityId
        )
    }
}