package com.everpath.domain.usecase.userprogress

import com.everpath.domain.repository.UserProgressRepository

/**
 * Caso de uso encargado de sincronizar
 * el progreso del usuario desde el
 * backend hacia Room.
 *
 * No devuelve información.
 * Su única responsabilidad es actualizar
 * la caché local.
 */
class FetchUserProgressUseCase(
    private val repository: UserProgressRepository
) {

    suspend operator fun invoke(
        userId: Long
    ) {

        repository.fetchUserProgress(
            userId
        )

    }

}