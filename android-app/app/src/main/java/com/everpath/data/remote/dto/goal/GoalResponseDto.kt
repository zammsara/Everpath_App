package com.everpath.data.remote.dto.goal

import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType

/**
 * DTO remoto que representa
 * la respuesta de una meta desde el backend.
 */
data class GoalResponseDto(

    val id: String,

    val title: String,

    val description: String,

    val lifeArea: LifeAreaType,

    val status: GoalStatus,

    val xpGranted: Boolean,

    val createdAt: String
)