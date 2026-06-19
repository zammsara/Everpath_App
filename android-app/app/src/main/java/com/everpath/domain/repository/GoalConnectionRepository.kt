package com.everpath.domain.repository

import com.everpath.domain.model.GoalConnection
import kotlinx.coroutines.flow.Flow

/**
 * Contrato de dominio encargado de gestionar las conexiones
 * entre metas dentro del grafo de progreso de Everpath.
 *
 * Define las operaciones disponibles para consultar,
 * crear y eliminar relaciones entre GoalNodes.
 */
interface GoalConnectionRepository {

    fun getGoalConnections():
            Flow<List<GoalConnection>>

    suspend fun saveConnection(
        connection: GoalConnection
    )

    suspend fun deleteConnection(
        connectionId: String
    )
}