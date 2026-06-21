package com.everpath.domain.repository

import com.everpath.domain.model.Achievement
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de gestionar
 * la persistencia de achievements.
 */
interface AchievementRepository {

    fun getAchievements():
            Flow<List<Achievement>>

    suspend fun getAchievementById(
        id: String
    ): Achievement?

    suspend fun saveAchievement(
        achievement: Achievement
    )
}