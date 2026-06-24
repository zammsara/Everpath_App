package com.everpath.domain.usecase.activity

import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository

class GetActivityByIdUseCase(
    private val activityRepository: ActivityRepository
) {

    suspend operator fun invoke(
        activityId: String
    ): Activity? {

        return activityRepository
            .getActivityById(activityId)
    }
}