package com.everpath.domain.repository

import com.everpath.domain.model.Activity
import kotlinx.coroutines.flow.Flow

/**
 * Contrato del repositorio de actividades
 * del dominio.
 *
 * Define todas las operaciones relacionadas
 * con actividades sin exponer detalles
 * de implementación como Room,
 * Retrofit o cualquier otra fuente
 * de datos.
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
    ): Activity


    suspend fun updateActivity(
        activity: Activity
    ): Activity


    suspend fun deleteActivity(
        activityId: String
    )
}