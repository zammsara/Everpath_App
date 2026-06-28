package com.everpath.domain.usecase.goal

import com.everpath.domain.repository.GoalRepository

/**
 * Caso de uso encargado de solicitar
 * la sincronización de todas las metas
 * desde el backend hacia Room.
 *
 * La UI continúa observando Room mediante
 * Flows mientras el repositorio actualiza
 * la información local.
 */
class FetchGoalsUseCase(

    private val goalRepository:
    GoalRepository

) {

    suspend operator fun invoke(
        userId: Long
    ) {
        goalRepository.fetchGoals(
            userId
        )
    }

}