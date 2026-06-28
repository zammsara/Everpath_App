package com.everpath.domain.usecase.goal

import com.everpath.domain.repository.GoalRepository

/**
 * Caso de uso encargado de solicitar
 * la sincronización de una meta
 * específica desde el backend hacia Room.
 *
 * La UI continúa observando Room mientras
 * el repositorio actualiza el registro local.
 */
class FetchGoalByIdUseCase(

    private val goalRepository:
    GoalRepository

) {

    suspend operator fun invoke(
        goalId: String
    ) {
        goalRepository.fetchGoalById(
            goalId
        )
    }

}