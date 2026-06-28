package com.everpath.domain.usecase.userprogress

import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso encargado de observar
 * continuamente el progreso del usuario
 * almacenado en Room.
 */
class GetUserProgressUseCase(
    private val repository: UserProgressRepository
) {

    operator fun invoke():
            Flow<UserProgress?>
    {
        return repository.observeUserProgress()

    }

}