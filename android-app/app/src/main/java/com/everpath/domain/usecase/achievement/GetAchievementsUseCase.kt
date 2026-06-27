package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository

/**
 * Caso de uso encargado de obtener
 * todos los achievements desbloqueados
 * por un usuario.
 */
class GetAchievementsUseCase(
    private val repository: AchievementRepository
) {

    suspend operator fun invoke(
        userId: Long
    ): List<Achievement> {

        return repository.getAchievementsByUser(
            userId
        )
    }
}