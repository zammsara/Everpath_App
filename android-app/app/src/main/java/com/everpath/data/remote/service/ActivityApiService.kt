package com.everpath.data.remote.service

import com.everpath.data.remote.dto.activity.ActivityResponseDto
import com.everpath.data.remote.dto.activity.CreateActivityRequestDto
import com.everpath.data.remote.dto.activity.UpdateActivityRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Servicio Retrofit encargado de declarar
 * todas las operaciones REST relacionadas
 * con actividades.
 *
 * Esta interfaz representa el contrato
 * entre Android y el backend de Everpath.
 */
interface ActivityApiService {


    @POST("api/v1/activities")
    suspend fun createActivity(

        @Body
        request: CreateActivityRequestDto

    ): ActivityResponseDto


    @GET("api/v1/activities/{activityId}")
    suspend fun getActivityById(

        @Path("activityId")
        activityId: String

    ): ActivityResponseDto


    @GET("api/v1/activities/goal/{goalId}")
    suspend fun getActivitiesByGoal(

        @Path("goalId")
        goalId: String

    ): List<ActivityResponseDto>


    @PUT("api/v1/activities/{activityId}")
    suspend fun updateActivity(

        @Path("activityId")
        activityId: String,

        @Body
        request: UpdateActivityRequestDto

    ): ActivityResponseDto


    @DELETE("api/v1/activities/{activityId}")
    suspend fun deleteActivity(

        @Path("activityId")
        activityId: String

    )
}