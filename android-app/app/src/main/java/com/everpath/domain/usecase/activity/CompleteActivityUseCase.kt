package com.everpath.domain.usecase.activity

import com.everpath.data.session.UserSession
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity
import com.everpath.domain.usecase.achievement.FetchAchievementsUseCase
import com.everpath.domain.usecase.userprogress.FetchUserProgressUseCase

/**
 * Caso de uso encargado de completar
 * una actividad y otorgar experiencia.
 */
class CompleteActivityUseCase(
    private val updateActivityUseCase: UpdateActivityUseCase,
    private val fetchUserProgressUseCase: FetchUserProgressUseCase,
    private val fetchAchievementsUseCase: FetchAchievementsUseCase
) {

    suspend operator fun invoke(
        activity: Activity
    ) {

        val wasXpGranted = activity.xpGranted

        val completedActivity =
            if (wasXpGranted) {
                activity.copy(
                    status =
                        ActivityStatus.COMPLETED
                )

            } else {

                activity.copy(
                    status =
                        ActivityStatus.COMPLETED,

                    xpGranted = true
                )
            }

        updateActivityUseCase(
            completedActivity
        )

        if (!wasXpGranted) {

            fetchUserProgressUseCase()
            fetchAchievementsUseCase(
                UserSession.userId
            )

        }
    }
}