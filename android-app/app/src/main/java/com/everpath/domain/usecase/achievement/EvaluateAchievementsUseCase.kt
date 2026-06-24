package com.everpath.domain.usecase.achievement

import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.model.Achievement
import com.everpath.domain.model.GoalNode

/**
 * Caso de uso encargado de evaluar
 * qué achievements han sido
 * desbloqueados por el usuario.
 *
 * Toda la lógica de desbloqueo
 * vive exclusivamente en dominio.
 */
class EvaluateAchievementsUseCase {

    operator fun invoke(
        goals: List<GoalNode>,
        xp: Int,
        level: Int
    ): List<Achievement> {

        val completedActivities =
            goals.sumOf { goal ->
                goal.activities.count {
                    it.status ==
                            ActivityStatus.COMPLETED
                }
            }

        val completedGoals =
            goals.count {
                it.status ==
                        GoalStatus.COMPLETED
            }

        return AchievementDefinitions
            .achievements
            .map { achievement ->

                achievement.copy(

                    unlocked =
                        isUnlocked(
                            achievementId =
                                achievement.id,

                            completedActivities =
                                completedActivities,

                            completedGoals =
                                completedGoals,

                            xp = xp,

                            level = level
                        )
                )
            }
    }

    private fun isUnlocked(
        achievementId: String,
        completedActivities: Int,
        completedGoals: Int,
        xp: Int,
        level: Int
    ): Boolean {

        return when (achievementId) {

            "ACTIVITY_1" ->
                completedActivities >= 1

            "ACTIVITY_10" ->
                completedActivities >= 10

            "ACTIVITY_50" ->
                completedActivities >= 50

            "GOAL_1" ->
                completedGoals >= 1

            "GOAL_10" ->
                completedGoals >= 10

            "XP_100" ->
                xp >= 100

            "XP_500" ->
                xp >= 500

            "XP_1000" ->
                xp >= 1000

            "LEVEL_5" ->
                level >= 5

            else ->
                false
        }
    }
}