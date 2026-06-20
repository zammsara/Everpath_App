package com.everpath.domain.model

/**
 * Representa el progreso actual
 * del usuario dentro de un nivel.
 *
 * Permite conocer cuánto avance
 * existe hacia el siguiente nivel.
 */
data class LevelProgress(

    val currentLevel: Int,

    val currentXp: Int,

    val currentLevelXp: Int,

    val requiredXpForNextLevel: Int,

    val progress: Float

)