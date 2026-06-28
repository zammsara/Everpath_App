package com.everpath.data.repository

import android.util.Log
import com.everpath.data.local.dao.ActivityDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.ActivityRemoteDataSource
import com.everpath.data.remote.mapper.toCreateRequestDto
import com.everpath.data.remote.mapper.toDomain
import com.everpath.data.remote.mapper.toUpdateRequestDto
import com.everpath.data.remote.util.safeApiCall
import com.everpath.domain.model.Activity
import com.everpath.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repositorio encargado de coordinar la obtención,
 * creación, actualización y eliminación de actividades.
 *
 * Esta implementación centraliza la comunicación entre
 * la fuente remota (Retrofit) y el almacenamiento local (Room),
 * manteniendo sincronizada la información utilizada por
 * el resto de la aplicación.
 */
class ActivityRepositoryImpl(

    private val activityDao: ActivityDao,

    private val remoteDataSource: ActivityRemoteDataSource

) : ActivityRepository {

    /**
     * Obtiene una actividad desde el backend
     * y actualiza la copia local.
     */
    override suspend fun getActivityById(
        activityId: String
    ): Activity {

        val result =
            safeApiCall(
                tag = "ActivityRepository"
            ) {

                remoteDataSource
                    .getActivityById(
                        activityId
                    )
            }

        val remoteActivity =
            result.getOrElse {

                Log.i(
                    "ActivityRepository",
                    "No fue posible obtener la actividad desde el servidor."
                )

                return activityDao
                    .getActivityById(activityId)
                    ?.toDomain()
                    ?: throw it
            }

        activityDao.upsertActivity(
            remoteActivity
                .toDomain()
                .toEntity()
        )
        return remoteActivity.toDomain()
    }

    /**
     * Observa continuamente las actividades
     * almacenadas en Room para una meta.
     */
    override fun observeActivitiesByGoal(
        goalId: String
    ): Flow<List<Activity>> {

        return activityDao
            .getActivitiesByGoalId(goalId)
            .map { activities ->

                activities.map {
                    it.toDomain()
                }

            }
    }


    /**
     * Descarga todas las actividades de una meta
     * desde el backend y actualiza la copia
     * almacenada en Room.
     */
    override suspend fun fetchActivitiesByGoal(
        goalId: String
    ) {

        val result =
            safeApiCall(
                tag = "ActivityRepository"
            ) {

                remoteDataSource
                    .getActivitiesByGoal(
                        goalId
                    )
            }

        if (result.isFailure) {
            Log.i(
                "ActivityRepository",
                "Sincronización omitida. Se utilizarán las actividades almacenadas localmente."
            )
            return
        }

        val remoteActivities =
            result.getOrThrow()

        activityDao.insertActivities(
            remoteActivities.map {
                it.toDomain()
                    .toEntity()
            }
        )
    }


    /**
     * Crea una nueva actividad en el backend,
     * posteriormente actualiza la copia local.
     */
    override suspend fun createActivity(
        activity: Activity
    ): Activity {

        val result =
            safeApiCall(
                tag = "ActivityRepository"
            ) {
                remoteDataSource
                    .createActivity(
                        activity.toCreateRequestDto()
                    )
            }

        val createdActivity =
            result.getOrElse {
                Log.i(
                    "ActivityRepository",
                    "No fue posible crear la actividad en el servidor."
                )
                return activity
            }

        activityDao.upsertActivity(
            createdActivity
                .toDomain()
                .toEntity()
        )

        return createdActivity.toDomain()
    }

    /**
     * Actualiza una actividad existente en el backend
     * y sincroniza la base de datos local.
     */
    override suspend fun updateActivity(
        activity: Activity
    ): Activity {

        val result =
            safeApiCall(
                tag = "ActivityRepository"
            ) {
                remoteDataSource
                    .updateActivity(
                        activity.id,
                        activity.toUpdateRequestDto()
                    )
            }

        val updatedActivity =
            result.getOrElse {
                Log.i(
                    "ActivityRepository",
                    "No fue posible actualizar la actividad en el servidor."
                )
                return activity
            }

        activityDao.upsertActivity(
            updatedActivity
                .toDomain()
                .toEntity()
        )
        return updatedActivity.toDomain()
    }

    /**
     * Elimina una actividad del backend
     * y posteriormente de Room.
     */
    override suspend fun deleteActivity(
        activityId: String
    ) {

        val result =
            safeApiCall(
                tag = "ActivityRepository"
            ) {
                remoteDataSource
                    .deleteActivity(
                        activityId
                    )
            }

        if (result.isFailure) {
            Log.i(
                "ActivityRepository",
                "No fue posible eliminar la actividad del servidor."
            )
            return
        }
        activityDao.deleteActivityById(
            activityId
        )
    }

}