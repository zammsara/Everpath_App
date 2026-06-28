package com.everpath.domain.usecase.achievement

import com.everpath.domain.repository.AchievementRepository

/**
 * Caso de uso encargado de sincronizar
 * los achievements del usuario desde
 * el backend hacia Room.
 *
 * No devuelve información directamente;
 * únicamente actualiza la base de datos
 * local para que los observadores sean
 * notificados automáticamente.
 */
class FetchAchievementsUseCase(
    private val repository: AchievementRepository
) {

    suspend operator fun invoke(
        userId: Long
    ) {

        repository.fetchAchievements(
            userId
        )

    }

}