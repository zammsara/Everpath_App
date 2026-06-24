package com.everpath.domain.usecase.userprogress

import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository

/**
 * Agrega experiencia al
 * progreso global del usuario.
 */
class AddXpUseCase(
    private val repository:
    UserProgressRepository
) {

    suspend operator fun invoke(
        xpAmount: Int
    ) {

        val currentProgress =
            repository.getCurrentUserProgress()

        if (currentProgress == null) {
            repository.saveUserProgress(
                UserProgress(
                    xp = xpAmount
                )
            )
        } else {

            repository.updateUserProgress(
                currentProgress.copy(
                    xp =
                        currentProgress.xp +
                                xpAmount
                )
            )
        }
    }
}