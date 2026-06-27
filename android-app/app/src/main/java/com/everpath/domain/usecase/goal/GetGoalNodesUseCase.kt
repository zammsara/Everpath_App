package com.everpath.domain.usecase.goal

import com.everpath.domain.model.GoalNode
import com.everpath.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso encargado de observar
 * continuamente las metas almacenadas
 * localmente.
 *
 * La UI consume este Flow mientras
 * el repositorio mantiene sincronizada
 * la información mediante Room.
 */
class GetGoalNodesUseCase(
    private val goalRepository: GoalRepository
) {

    operator fun invoke(): Flow<List<GoalNode>> {

        return goalRepository
            .observeGoals()

    }
}