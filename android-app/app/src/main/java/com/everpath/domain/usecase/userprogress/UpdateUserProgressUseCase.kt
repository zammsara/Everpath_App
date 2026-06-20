package com.everpath.domain.usecase.userprogress

import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository

/**
 * Actualiza el progreso global
 * del usuario.
 */
class UpdateUserProgressUseCase(
    private val repository: UserProgressRepository
) {

    suspend operator fun invoke(
        userProgress: UserProgress
    ) {
        repository.updateUserProgress(
            userProgress
        )
    }
}