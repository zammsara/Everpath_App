package com.everpath.data.remote.dto.activity

import com.everpath.domain.enums.ActivityStatus

/**
 * DTO encargado de representar
 * la información necesaria para
 * actualizar una actividad.
 *
 * Refleja exactamente el contrato
 * esperado por Spring Boot.
 */
data class UpdateActivityRequestDto(

    val title: String,

    val description: String,

    val status: ActivityStatus
)