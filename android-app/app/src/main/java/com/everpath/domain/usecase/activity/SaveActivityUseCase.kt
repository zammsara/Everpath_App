package com.everpath.domain.usecase.activity

import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository

class SaveActivityUseCase(
    private val activityRepository: ActivityRepository
) {

    suspend operator fun invoke(
        activity: Activity
    ) {

        activityRepository
            .saveActivity(activity)
    }
}