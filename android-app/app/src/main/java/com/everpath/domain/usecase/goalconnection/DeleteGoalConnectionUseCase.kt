package com.everpath.domain.usecase.goalconnection

import com.everpath.domain.repository.GoalConnectionRepository

/**
 * Caso de uso encargado de eliminar una
 * conexión existente del grafo de metas.
 */
class DeleteGoalConnectionUseCase(
    private val goalConnectionRepository:
    GoalConnectionRepository
) {

    suspend operator fun invoke(
        connectionId: String
    ) {

        goalConnectionRepository
            .deleteConnection(connectionId)
    }
}