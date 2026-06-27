package com.everpath.data.remote.mapper

import com.everpath.data.remote.dto.goal.CreateGoalRequestDto
import com.everpath.data.remote.dto.goal.GoalResponseDto
import com.everpath.data.remote.dto.goal.UpdateGoalRequestDto
import com.everpath.domain.model.GoalNode

/**
 * Mapper encargado de transformar
 * objetos remotos relacionados
 * con metas hacia el dominio
 * y viceversa.
 *
 * Centraliza toda la conversión
 * entre DTOs utilizados por Retrofit
 * y modelos del dominio.
 */

/**
 * Convierte una respuesta del backend
 * en un modelo del dominio.
 */
fun GoalResponseDto.toDomain(): GoalNode {

    return GoalNode(

        id = id,

        title = title,

        description = description,

        lifeArea = lifeArea,

        status = status,

        activities = emptyList(),

        progress = 0f,

        xpGranted = xpGranted
    )
}

/**
 * Convierte una meta del dominio
 * en el DTO utilizado para crear
 * una nueva meta en el backend.
 *
 * El userId se recibe como parámetro
 * porque no forma parte del modelo
 * GoalNode.
 */
fun GoalNode.toCreateRequestDto(
    userId: Long
): CreateGoalRequestDto {

    return CreateGoalRequestDto(

        userId = userId,

        title = title,

        description = description,

        lifeArea = lifeArea
    )
}

/**
 * Convierte una meta del dominio
 * en el DTO utilizado para
 * actualizar una meta existente.
 */
fun GoalNode.toUpdateRequestDto():
        UpdateGoalRequestDto {

    return UpdateGoalRequestDto(

        title = title,

        description = description,

        lifeArea = lifeArea,

        status = status
    )
}