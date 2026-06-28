package com.everpath.domain.usecase.activity

import com.everpath.domain.repository.ActivityRepository

/**
 * Sincroniza una actividad desde
 * el backend hacia Room.
 *
 * No devuelve datos.
 * Únicamente actualiza la base
 * de datos local.
 */
class FetchActivityByIdUseCase(

    private val repository:
    ActivityRepository

) {

    suspend operator fun invoke(
        activityId: String
    ) {

        repository.getActivityById(
            activityId
        )

    }

}