package com.everpath.domain.model

/**
 * Representa el progreso global
 * del usuario dentro de Everpath.
 *
 * Será utilizado por XP,
 * niveles y logros.
 */
data class UserProgress(

    val id: Int = 1,

    val xp: Int

)