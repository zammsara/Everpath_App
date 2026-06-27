package com.everpath.data.remote.dto.progress

/**
 * DTO encargado de representar
 * la respuesta del backend
 * relacionada con el progreso
 * global del usuario.
 *
 * Refleja exactamente el contrato
 * expuesto por Spring Boot.
 */
data class UserProgressResponseDto(

    val xp: Int,

    val level: Int,

    val currentLevelXp: Int,

    val requiredXpForNextLevel: Int,

    val progress: Float
)