package com.everpath.domain.repository

import com.everpath.domain.model.Achievement

/**
 * Contrato del repositorio encargado de gestionar
 * la información de achievements del usuario.
 *
 * Este repositorio representa únicamente las
 * operaciones del dominio, sin exponer detalles
 * sobre si los datos provienen del backend REST,
 * Room o una estrategia híbrida Offline First.
 */
interface AchievementRepository {


    suspend fun getAchievementsByUser(
        userId: Long
    ): List<Achievement>


    suspend fun getAchievementById(
        achievementId: String
    ): Achievement?

    /**
     * Sincroniza un achievement
     * desbloqueado con el backend
     * y la persistencia local.
     */
    suspend fun saveAchievement(
        achievement: Achievement
    )
}