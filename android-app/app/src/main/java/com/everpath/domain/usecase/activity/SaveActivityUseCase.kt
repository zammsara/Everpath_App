package com.everpath.domain.usecase.activity

import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository

/**
 * Caso de uso encargado de crear
 * una actividad en el backend.
 */
class SaveActivityUseCase(
    private val activityRepository: ActivityRepository
) {

    suspend operator fun invoke(
        activity: Activity
    ) {
        activityRepository
            .createActivity(activity)

    }
}