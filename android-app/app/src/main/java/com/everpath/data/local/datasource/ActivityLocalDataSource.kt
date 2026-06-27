package com.everpath.data.local.datasource

import com.everpath.data.local.dao.ActivityDao
import com.everpath.data.local.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

/**
 * DataSource local encargado de centralizar
 * todas las operaciones relacionadas con
 * actividades almacenadas mediante Room.
 *
 * Este componente actúa como la única capa
 * de acceso a ActivityDao, evitando que los
 * repositorios dependan directamente de Room
 * y facilitando la integración futura con
 * fuentes de datos remotas.
 */
class ActivityLocalDataSource(
    private val activityDao: ActivityDao
) {


    fun getActivitiesByGoalId(
        goalId: String
    ): Flow<List<ActivityEntity>> {

        return activityDao.getActivitiesByGoalId(
            goalId
        )
    }


    suspend fun getActivityById(
        activityId: String
    ): ActivityEntity? {

        return activityDao.getActivityById(
            activityId
        )
    }

    /**
     * Inserta una nueva actividad
     * en la base de datos local.
     */
    suspend fun insertActivity(
        activity: ActivityEntity
    ) {

        activityDao.insertActivity(
            activity
        )
    }


    suspend fun updateActivity(
        activity: ActivityEntity
    ) {

        activityDao.updateActivity(
            activity
        )
    }


    suspend fun deleteActivityById(
        activityId: String
    ) {

        activityDao.deleteActivityById(
            activityId
        )
    }
}