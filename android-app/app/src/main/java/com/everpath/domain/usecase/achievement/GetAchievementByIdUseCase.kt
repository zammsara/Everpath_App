package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository

/**
 * Obtiene un achievement
 * específico por id.
 */
class GetAchievementByIdUseCase(
    private val repository:
    AchievementRepository
) {

    suspend operator fun invoke(
        id: String
    ): Achievement? {
        return repository
            .getAchievementById(id)
    }
}