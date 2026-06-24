package com.everpath.domain.usecase.goalconnection

import com.everpath.domain.model.GoalConnection
import com.everpath.domain.repository.GoalConnectionRepository

/**
 * Caso de uso encargado de guardar una nueva
 * conexión entre metas dentro del grafo.
 */
class SaveGoalConnectionUseCase(
    private val goalConnectionRepository:
    GoalConnectionRepository
) {

    suspend operator fun invoke(
        connection: GoalConnection
    ) {

        goalConnectionRepository
            .saveConnection(connection)
    }
}