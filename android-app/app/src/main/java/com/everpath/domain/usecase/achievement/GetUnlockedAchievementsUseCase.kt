package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso encargado de obtener
 * los achievements persistidos
 * dentro de Room.
 */
class GetUnlockedAchievementsUseCase(
    private val repository: AchievementRepository
) {

    operator fun invoke():
            Flow<List<Achievement>> {

        return repository.getAchievements()
    }
}