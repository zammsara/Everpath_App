package com.everpath.domain.repository

import com.everpath.domain.model.Achievement

/**
 * Contrato del repositorio encargado de consultar
 * los achievements desbloqueados por un usuario.
 *
 * Los achievements representan un estado derivado
 * calculado automáticamente por el backend a partir
 * del progreso del usuario, por lo que este
 * repositorio únicamente expone operaciones
 * de lectura.
 */
interface AchievementRepository {

    /**
     * Obtiene todos los achievements
     * desbloqueados por un usuario.
     */
    suspend fun getAchievementsByUser(
        userId: Long
    ): List<Achievement>

    /**
     * Obtiene un achievement
     * previamente sincronizado.
     */
    suspend fun getAchievementById(
        achievementId: String
    ): Achievement?
}