package com.everpath.domain.usecase.activity

import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class GetActivityByIdUseCase(
    private val activityRepository: ActivityRepository
) {

    operator fun invoke(
        activityId: String
    ): Flow<Activity?> {

        return activityRepository.observeActivityById(
            activityId
        )

    }
}