package com.everpath.data.remote.datasource

import com.everpath.data.remote.dto.activity.ActivityResponseDto
import com.everpath.data.remote.dto.activity.CreateActivityRequestDto
import com.everpath.data.remote.dto.activity.UpdateActivityRequestDto
import com.everpath.data.remote.service.ActivityApiService

/**
 * DataSource remoto encargado de encapsular
 * todas las operaciones HTTP relacionadas
 * con actividades dentro de Everpath.
 *
 * Su responsabilidad es actuar como una capa
 * intermedia entre los repositorios y Retrofit,
 * evitando que la capa de datos dependa
 * directamente del servicio REST.
 */
class ActivityRemoteDataSource(
    private val activityApiService: ActivityApiService
) {


    suspend fun getActivityById(
        activityId: String
    ): ActivityResponseDto {

        return activityApiService.getActivityById(
            activityId
        )
    }


    suspend fun getActivitiesByGoal(
        goalId: String
    ): List<ActivityResponseDto> {

        return activityApiService.getActivitiesByGoal(
            goalId
        )
    }


    suspend fun createActivity(
        request: CreateActivityRequestDto
    ): ActivityResponseDto {

        return activityApiService.createActivity(
            request
        )
    }


    suspend fun updateActivity(
        activityId: String,
        request: UpdateActivityRequestDto
    ): ActivityResponseDto {

        return activityApiService.updateActivity(
            activityId,
            request
        )
    }


    suspend fun deleteActivity(
        activityId: String
    ) {

        activityApiService.deleteActivity(
            activityId
        )
    }
}