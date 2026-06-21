package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository

/**
 * Caso de uso encargado de guardar
 * achievements en persistencia.
 */
class SaveAchievementUseCase(
    private val repository: AchievementRepository
) {

    suspend operator fun invoke(
        achievement: Achievement
    ) {
        repository.saveAchievement(
            achievement
        )
    }
}