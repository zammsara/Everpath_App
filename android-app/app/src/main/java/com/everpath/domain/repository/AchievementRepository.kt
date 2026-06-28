package com.everpath.domain.repository

import com.everpath.domain.model.Achievement
import kotlinx.coroutines.flow.Flow

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
     * Observa continuamente los achievements
     * almacenados en Room.
     *
     * La UI consumirá este Flow para mantenerse
     * sincronizada automáticamente con la base
     * de datos local.
     */
    fun observeAchievements():
            Flow<List<Achievement>>

    /**
     * Sincroniza los achievements del usuario
     * desde el backend hacia Room.
     */
    suspend fun fetchAchievements(
        userId: Long
    )

    /**
     * Obtiene un achievement
     * previamente sincronizado.
     */
    suspend fun getAchievementById(
        achievementId: String
    ): Achievement?
}