package com.everpath.data.remote.mapper

import com.everpath.data.remote.dto.activity.ActivityResponseDto
import com.everpath.data.remote.dto.activity.CreateActivityRequestDto
import com.everpath.data.remote.dto.activity.UpdateActivityRequestDto
import com.everpath.domain.model.Activity

/**
 * Mapper encargado de convertir entre
 * los DTOs remotos de actividades y
 * el modelo de dominio utilizado por
 * la aplicación.
 *
 * Centraliza todas las conversiones
 * relacionadas con la comunicación
 * entre Retrofit y la capa de dominio.
 */

fun ActivityResponseDto.toDomain(): Activity {

    return Activity(
        id = id,
        goalId = goalId,
        title = title,
        description = description,
        status = status,
        xpGranted = xpGranted
    )
}


fun Activity.toCreateRequestDto(): CreateActivityRequestDto {

    return CreateActivityRequestDto(
        goalId = goalId,
        title = title,
        description = description
    )
}


fun Activity.toUpdateRequestDto(): UpdateActivityRequestDto {

    return UpdateActivityRequestDto(
        title = title,
        description = description,
        status = status
    )
}