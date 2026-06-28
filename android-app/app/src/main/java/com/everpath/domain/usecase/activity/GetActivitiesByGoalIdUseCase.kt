package com.everpath.domain.usecase.activity

import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso encargado de observar
 * continuamente las actividades de
 * una meta desde Room.
 */
class GetActivitiesByGoalIdUseCase(
    private val activityRepository: ActivityRepository
) {

    operator fun invoke(
        goalId: String
    ): Flow<List<Activity>> {

        return activityRepository
            .observeActivitiesByGoal(
                goalId
            )

    }
}