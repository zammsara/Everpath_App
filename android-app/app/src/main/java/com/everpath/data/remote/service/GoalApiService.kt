package com.everpath.data.remote.service

import com.everpath.data.remote.dto.goal.CreateGoalRequestDto
import com.everpath.data.remote.dto.goal.GoalResponseDto
import com.everpath.data.remote.dto.goal.UpdateGoalRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Servicio encargado de definir
 * todas las operaciones REST
 * relacionadas con metas.
 *
 * Esta interfaz representa el contrato
 * de comunicación entre Android
 * y el backend Spring Boot.
 */
interface GoalApiService {


    @GET("api/v1/goals/{goalId}")
    suspend fun getGoalById(
        @Path("goalId")
        goalId: String
    ): GoalResponseDto


    @GET("api/v1/goals/user/{userId}")
    suspend fun getGoalsByUser(
        @Path("userId")
        userId: Long
    ): List<GoalResponseDto>


    @POST("api/v1/goals")
    suspend fun createGoal(
        @Body
        request: CreateGoalRequestDto
    ): GoalResponseDto


    @PUT("api/v1/goals/{goalId}")
    suspend fun updateGoal(
        @Path("goalId")
        goalId: String,

        @Body
        request: UpdateGoalRequestDto
    ): GoalResponseDto


    @DELETE("api/v1/goals/{goalId}")
    suspend fun deleteGoal(
        @Path("goalId")
        goalId: String
    )
}