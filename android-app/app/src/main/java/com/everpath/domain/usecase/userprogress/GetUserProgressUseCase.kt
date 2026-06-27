package com.everpath.domain.usecase.userprogress

import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository

/**
 * Caso de uso encargado de obtener
 * el progreso global del usuario.
 *
 * La implementación del repositorio
 * será responsable de decidir si la
 * información proviene de Room,
 * del backend o de una combinación
 * de ambas fuentes.
 */
class GetUserProgressUseCase(
    private val repository: UserProgressRepository
) {

    suspend operator fun invoke(
        userId: Long
    ): UserProgress {

        return repository.getUserProgress(
            userId
        )

    }

}