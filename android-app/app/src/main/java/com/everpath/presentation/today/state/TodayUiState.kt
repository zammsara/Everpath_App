package com.everpath.presentation.today.state

import com.everpath.domain.model.GoalNode

/**
 * Estado de la pantalla Dashboard.
 *
 * Centraliza todas las métricas y datos
 * necesarios para representar el resumen
 * general del usuario dentro de Everpath.
 */
data class TodayUiState(

    val goalCount: Int = 0,

    val completedGoalCount: Int = 0,

    val activityCount: Int = 0,

    val completedActivityCount: Int = 0,

    val globalProgress: Float = 0f,

    val activeGoals: List<GoalNode> = emptyList(),

    val xp: Int = 0,

    val level: Int = 1,

    val isLoading: Boolean = true

)