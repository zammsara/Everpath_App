package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement
import com.everpath.domain.model.GoalNode

/**
 * Caso de uso encargado de detectar
 * achievements nuevos y persistirlos.
 */
class UnlockAchievementsUseCase(
    private val evaluateAchievementsUseCase:
    EvaluateAchievementsUseCase,

    private val saveAchievementUseCase:
    SaveAchievementUseCase,

    private val achievementRepository:
    com.everpath.domain.repository.AchievementRepository
) {

    suspend operator fun invoke(
        goals: List<GoalNode>,
        xp: Int,
        level: Int
    ) {

        val evaluatedAchievements =
            evaluateAchievementsUseCase(
                goals = goals,
                xp = xp,
                level = level
            )

        evaluatedAchievements
            .filter { it.unlocked }
            .forEach { achievement ->

                val existingAchievement =
                    achievementRepository
                        .getAchievementById(
                            achievement.id
                        )

                if (
                    existingAchievement == null
                ) {

                    saveAchievementUseCase(
                        achievement.copy(
                            unlockedAt =
                                System.currentTimeMillis()
                        )
                    )
                }
            }
    }
}