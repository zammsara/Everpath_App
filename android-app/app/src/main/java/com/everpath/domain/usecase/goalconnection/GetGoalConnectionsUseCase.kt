package com.everpath.domain.usecase.goalconnection

import com.everpath.domain.model.GoalConnection
import com.everpath.domain.repository.GoalConnectionRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso encargado de obtener todas las
 * conexiones registradas en el grafo de metas.
 */
class GetGoalConnectionsUseCase(
    private val goalConnectionRepository:
    GoalConnectionRepository
) {

    operator fun invoke():
            Flow<List<GoalConnection>> {

        return goalConnectionRepository
            .getGoalConnections()
    }
}