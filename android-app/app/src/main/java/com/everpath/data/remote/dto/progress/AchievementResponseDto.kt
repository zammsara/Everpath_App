package com.everpath.data.remote.dto.progress

/**
 * DTO encargado de representar
 * un achievement recibido
 * desde el backend.
 *
 * Refleja exactamente el contrato
 * expuesto por Spring Boot.
 */
data class AchievementResponseDto(

    val id: String,

    val title: String,

    val description: String,

    val unlocked: Boolean,

    /**
     * Fecha en formato ISO-8601
     * enviada por Spring Boot.
     *
     * Se mantiene como String
     * para evitar lógica de
     * conversión en la capa Remote.
     */
    val unlockedAt: String?
)