package com.everpath.data.remote.dto.activity

/**
 * DTO encargado de representar
 * la información necesaria para
 * crear una nueva actividad
 * desde Android.
 *
 * Refleja exactamente el contrato
 * esperado por Spring Boot.
 */
data class CreateActivityRequestDto(

    val goalId: String,

    val title: String,

    val description: String
)