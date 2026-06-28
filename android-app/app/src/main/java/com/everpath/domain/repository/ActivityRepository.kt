package com.everpath.domain.repository

import com.everpath.domain.model.Activity
import kotlinx.coroutines.flow.Flow

/**
 * Contrato del repositorio encargado de gestionar
 * actividades siguiendo la arquitectura
 * Offline First de Everpath.
 *
 * La interfaz de usuario observa únicamente
 * Room mediante Flow.
 *
 * Los métodos fetch sincronizan información
 * desde el backend hacia la base local.
 *
 * Las operaciones create, update y delete
 * sincronizan primero el servidor y después
 * actualizan Room utilizando la respuesta
 * oficial del backend.
 */
interface ActivityRepository {

    suspend fun getActivityById(
        activityId: String
    ): Activity


    /**
     * Observa continuamente las actividades
     * pertenecientes a una meta almacenadas
     * en la base de datos local.
     */
    fun observeActivitiesByGoal(
        goalId: String
    ): Flow<List<Activity>>

    /**
     * Sincroniza desde el backend todas las
     * actividades pertenecientes a una meta
     * y actualiza la base de datos local.
     */
    suspend fun fetchActivitiesByGoal(
        goalId: String
    )

    /**
     * Observa continuamente una actividad
     * almacenada localmente.
     *
     * La UI deberá consumir este Flow para
     * mantenerse sincronizada automáticamente
     * con Room.
     */
    fun observeActivityById(
        activityId: String
    ): Flow<Activity?>


    suspend fun createActivity(
        activity: Activity
    )


    suspend fun updateActivity(
        activity: Activity
    )


    suspend fun deleteActivity(
        activityId: String
    )
}