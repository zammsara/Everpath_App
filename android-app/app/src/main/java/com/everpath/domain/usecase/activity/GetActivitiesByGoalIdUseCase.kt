package com.everpath.domain.usecase.activity

import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository

/**
 * Caso de uso encargado de obtener
 * las actividades pertenecientes
 * a una meta.
 */
class GetActivitiesByGoalIdUseCase(
    private val activityRepository: ActivityRepository
) {

    suspend operator fun invoke(
        goalId: String
    ): List<Activity> {

        return activityRepository
            .getActivitiesByGoal(goalId)

    }
}