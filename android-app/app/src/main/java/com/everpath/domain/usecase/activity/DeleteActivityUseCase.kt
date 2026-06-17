package com.everpath.domain.usecase.activity

import com.everpath.domain.repository.ActivityRepository

class DeleteActivityUseCase(
    private val activityRepository: ActivityRepository
) {

    suspend operator fun invoke(
        activityId: String
    ) {

        activityRepository
            .deleteActivity(activityId)
    }
}