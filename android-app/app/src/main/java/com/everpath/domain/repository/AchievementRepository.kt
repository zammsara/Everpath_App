package com.everpath.domain.repository

import com.everpath.domain.model.Achievement
import kotlinx.coroutines.flow.Flow

/**
 * Contrato encargado de gestionar los
 * achievements del usuario siguiendo
 * la estrategia Offline First.
 *
 * Los achievements son calculados por
 * el backend y almacenados localmente
 * para que la interfaz observe
 * exclusivamente Room.
 *
 * La sincronización se realiza mediante
 * operaciones fetch mientras la lectura
 * continua se realiza mediante Flow.
 */
interface AchievementRepository {

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