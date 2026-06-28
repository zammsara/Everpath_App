package com.everpath.domain.usecase.activity

import com.everpath.domain.repository.ActivityRepository

/**
 * Caso de uso encargado de sincronizar
 * las actividades de una meta desde
 * el backend hacia Room.
 *
 * No devuelve información directamente;
 * únicamente actualiza la base de datos
 * local para que los observadores sean
 * notificados automáticamente.
 */
class FetchActivitiesByGoalUseCase(
    private val activityRepository: ActivityRepository
) {

    suspend operator fun invoke(
        goalId: String
    ) {

        activityRepository
            .fetchActivitiesByGoal(
                goalId
            )

    }

}