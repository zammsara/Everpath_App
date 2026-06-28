package com.everpath.domain.repository

import com.everpath.domain.model.GoalNode
import kotlinx.coroutines.flow.Flow

/**
 * Contrato del repositorio encargado de gestionar
 * las metas de Everpath siguiendo una estrategia
 * Offline First.
 *
 * Todos los repositorios híbridos del proyecto
 * siguen la misma estructura:
 *
 * 1. observe...()
 *    Expone información almacenada en Room
 *    mediante Flow para mantener sincronizada
 *    la interfaz de usuario.
 *
 * 2. fetch...()
 *    Descarga información desde el backend
 *    y actualiza exclusivamente la base de
 *    datos local.
 *
 * 3. create / update / delete
 *    Ejecutan la operación remota y utilizan
 *    la respuesta oficial del servidor para
 *    sincronizar posteriormente Room.
 *
 * La UI nunca consume directamente respuestas
 * provenientes de la API REST.
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