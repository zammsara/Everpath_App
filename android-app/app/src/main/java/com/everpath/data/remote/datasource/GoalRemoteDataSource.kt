package com.everpath.data.remote.datasource

import com.everpath.data.remote.dto.goal.CreateGoalRequestDto
import com.everpath.data.remote.dto.goal.GoalResponseDto
import com.everpath.data.remote.dto.goal.UpdateGoalRequestDto
import com.everpath.data.remote.service.GoalApiService

/**
 * DataSource remoto encargado de encapsular
 * todas las operaciones HTTP relacionadas
 * con metas dentro de Everpath.
 *
 * Su responsabilidad es actuar como una capa
 * intermedia entre los repositorios y Retrofit,
 * evitando que la capa de datos dependa
 * directamente de la implementación del servicio REST.
 */
class GoalRemoteDataSource(
    private val goalApiService: GoalApiService
) {


    suspend fun getGoalById(
        goalId: String
    ): GoalResponseDto {

        return goalApiService.getGoalById(
            goalId
        )
    }


    suspend fun getGoalsByUser(
        userId: Long
    ): List<GoalResponseDto> {

        return goalApiService.getGoalsByUser(
            userId
        )
    }


    suspend fun createGoal(
        request: CreateGoalRequestDto
    ): GoalResponseDto {

        return goalApiService.createGoal(
            request
        )
    }


    suspend fun updateGoal(
        goalId: String,
        request: UpdateGoalRequestDto
    ): GoalResponseDto {

        return goalApiService.updateGoal(
            goalId,
            request
        )
    }


    suspend fun deleteGoal(
        goalId: String
    ) {

        goalApiService.deleteGoal(
            goalId
        )
    }
}