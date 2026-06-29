package com.everpath.data.sync

import com.everpath.data.session.UserSession
import com.everpath.domain.repository.AchievementRepository
import com.everpath.domain.repository.ActivityRepository
import com.everpath.domain.repository.GoalRepository
import com.everpath.domain.repository.UserProgressRepository
import com.everpath.domain.sync.SyncManager
import kotlinx.coroutines.flow.first

/**
 * Implementación principal del mecanismo
 * de sincronización Offline First.
 *
 * Centraliza todas las descargas desde el
 * backend hacia la base de datos local.
 */
class SyncManagerImpl(
    private val goalRepository: GoalRepository,
    private val activityRepository: ActivityRepository,
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

        goalRepository
            .observeGoals()
            .first()
            .forEach { goal ->

                activityRepository
                    .fetchActivitiesByGoal(
                        goal.id
                    )
            }

        userProgressRepository.fetchUserProgress()
        achievementRepository.fetchAchievements(userId)

    }

}