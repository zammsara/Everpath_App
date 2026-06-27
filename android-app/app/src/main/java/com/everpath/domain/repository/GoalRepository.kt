package com.everpath.domain.repository

import com.everpath.domain.model.GoalNode
import kotlinx.coroutines.flow.Flow

/**
 * Contrato del repositorio encargado de gestionar
 * las operaciones relacionadas con metas dentro
 * del dominio de Everpath.
 *
 * El repositorio expone dos tipos de operaciones:
 *
 * - Observación local mediante Room (Flow).
 * - Sincronización con el backend REST.
 *
 * La implementación concreta será responsable
 * de coordinar Room y Retrofit manteniendo
 * una estrategia Offline First.
 */
interface GoalRepository {

    // ------------------------------------------------------------------------
    // Observación local (Room)
    // ------------------------------------------------------------------------

    /**
     * Observa continuamente todas las metas
     * almacenadas localmente.
     */
    fun observeGoals(): Flow<List<GoalNode>>

    /**
     * Observa una meta específica
     * almacenada localmente.
     */
    fun observeGoalById(
        goalId: String
    ): Flow<GoalNode?>

    // ------------------------------------------------------------------------
    // Sincronización remota (Backend REST)
    // ------------------------------------------------------------------------

    /**
     * Descarga todas las metas
     * del usuario desde el backend
     * y sincroniza la base local.
     */
    suspend fun fetchGoals(
        userId: Long
    )


    suspend fun fetchGoalById(
        goalId: String
    )

    /**
     * Crea una nueva meta
     * en el servidor.
     */
    suspend fun createGoal(
        goalNode: GoalNode,
        userId: Long
    )


    suspend fun updateGoal(
        goalNode: GoalNode
    )

    /**
     * Elimina una meta
     * tanto del servidor
     * como del almacenamiento local.
     */
    suspend fun deleteGoal(
        goalId: String
    )
}