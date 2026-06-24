package com.everpath.domain.usecase.userprogress

import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository
import kotlinx.coroutines.flow.Flow

/**
 * Obtiene el progreso global
 * del usuario almacenado.
 */
class GetUserProgressUseCase(
    private val repository: UserProgressRepository
) {

    operator fun invoke(): Flow<UserProgress?> {
        return repository.getUserProgress()
    }
}