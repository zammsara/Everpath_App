package com.everpath.data.sync

import com.everpath.data.session.UserSession
import com.everpath.domain.repository.AchievementRepository
import com.everpath.domain.repository.GoalRepository
import com.everpath.domain.repository.UserProgressRepository
import com.everpath.domain.sync.SyncManager

/**
 * Implementación principal del mecanismo
 * de sincronización Offline First.
 *
 * Centraliza todas las descargas desde el
 * backend hacia la base de datos local.
 */
class SyncManagerImpl(

    private val goalRepository: GoalRepository,
    private val userProgressRepository: UserProgressRepository,
    private val achievementRepository: AchievementRepository

) : SyncManager {

    /**
     * Ejecuta una sincronización completa
     * del usuario actual.
     */
    override suspend fun refresh() {

        val userId = UserSession.userId

        goalRepository.fetchGoals(userId)
        userProgressRepository.fetchUserProgress()
        achievementRepository.fetchAchievements(userId)

    }

}