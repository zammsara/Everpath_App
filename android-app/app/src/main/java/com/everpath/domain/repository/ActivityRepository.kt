package com.everpath.domain.repository

import com.everpath.domain.model.Activity

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


    suspend fun getActivitiesByGoal(
        goalId: String
    ): List<Activity>


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