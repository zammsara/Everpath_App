package com.everpath.presentation.profile.state

import com.everpath.domain.model.LevelProgress

/**
 * Estado principal de ProfileScreen.
 *
 * Contiene todas las estadísticas
 * necesarias para representar el
 * perfil del usuario.
 */
data class ProfileUiState(

    val goalCount: Int = 0,

    val completedGoalCount: Int = 0,

    val activityCount: Int = 0,

    val completedActivityCount: Int = 0,

    val globalProgress: Float = 0f,

    val xp: Int = 0,

    val level: Int = 1,

    val levelProgress: LevelProgress? = null,

    val isLoading: Boolean = true

)