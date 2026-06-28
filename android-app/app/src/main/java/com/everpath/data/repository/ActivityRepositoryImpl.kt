package com.everpath.data.repository

import com.everpath.data.local.dao.ActivityDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.ActivityRemoteDataSource
import com.everpath.data.remote.mapper.toCreateRequestDto
import com.everpath.data.remote.mapper.toDomain
import com.everpath.data.remote.mapper.toUpdateRequestDto
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

        val remoteActivity =
            remoteDataSource
                .getActivityById(activityId)
                .toDomain()

        activityDao.upsertActivity(
            remoteActivity.toEntity()
        )

        return remoteActivity
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
     * Crea una nueva actividad en el backend,
     * posteriormente actualiza la copia local.
     */
    override suspend fun createActivity(
        activity: Activity
    ): Activity {

        val createdActivity =
            remoteDataSource
                .createActivity(
                    activity.toCreateRequestDto()
                )
                .toDomain()

        activityDao.upsertActivity(
            createdActivity.toEntity()
        )

        return createdActivity
    }

    /**
     * Actualiza una actividad existente en el backend
     * y sincroniza la base de datos local.
     */
    override suspend fun updateActivity(
        activity: Activity
    ): Activity {

        val updatedActivity =
            remoteDataSource
                .updateActivity(
                    activity.id,
                    activity.toUpdateRequestDto()
                )
                .toDomain()

        activityDao.upsertActivity(
            updatedActivity.toEntity()
        )

        return updatedActivity
    }

    /**
     * Elimina una actividad del backend
     * y posteriormente de Room.
     */
    override suspend fun deleteActivity(
        activityId: String
    ) {

        remoteDataSource.deleteActivity(
            activityId
        )

        activityDao.deleteActivityById(
            activityId
        )
    }

}