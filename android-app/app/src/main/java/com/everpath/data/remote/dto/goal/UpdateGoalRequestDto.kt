package com.everpath.data.remote.dto.goal

import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType

/**
 * DTO remoto encargado de actualizar
 * una meta existente en el backend.
 */
data class UpdateGoalRequestDto(

    val title: String,

    val description: String,

    val lifeArea: LifeAreaType,

    val status: GoalStatus
)