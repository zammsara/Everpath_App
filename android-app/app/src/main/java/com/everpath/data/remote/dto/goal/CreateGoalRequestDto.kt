package com.everpath.data.remote.dto.goal

import com.everpath.domain.enums.LifeAreaType

/**
 * DTO remoto encargado de enviar
 * la información necesaria para crear una meta
 * hacia el backend Spring Boot.
 */
data class CreateGoalRequestDto(

    val userId: Long,

    val title: String,

    val description: String,

    val lifeArea: LifeAreaType
)