package com.everpath.data.remote.dto.activity

import com.everpath.domain.enums.ActivityStatus

/**
 * DTO encargado de representar
 * la respuesta del backend al
 * consultar una actividad.
 *
 * Refleja exactamente el contrato
 * expuesto por Spring Boot.
 */
data class ActivityResponseDto(

    val id: String,

    val goalId: String,

    val title: String,

    val description: String,

    val status: ActivityStatus,

    val xpGranted: Boolean,

    val createdAt: String
)