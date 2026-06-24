package com.everpath.domain.usecase.userprogress

import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository

/**
 * Guarda el progreso global
 * inicial del usuario.
 */
class SaveUserProgressUseCase(
    private val repository: UserProgressRepository
) {

    suspend operator fun invoke(
        userProgress: UserProgress
    ) {
        repository.saveUserProgress(
            userProgress
        )
    }
}