package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso encargado de observar
 * continuamente los achievements
 * almacenados localmente.
 */
class GetAchievementsUseCase(
    private val repository: AchievementRepository
) {

    operator fun invoke():
            Flow<List<Achievement>> {

        return repository.observeAchievements()

    }
}