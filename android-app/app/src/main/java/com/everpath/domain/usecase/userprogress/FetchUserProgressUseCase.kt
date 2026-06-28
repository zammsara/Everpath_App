package com.everpath.domain.usecase.userprogress

import com.everpath.domain.repository.UserProgressRepository

/**
 * Caso de uso encargado de solicitar
 * la sincronización del progreso del
 * usuario actualmente autenticado.
 *
 * No devuelve información.
 * Su única responsabilidad consiste
 * en actualizar la caché local
 * almacenada mediante Room.
 */
class FetchUserProgressUseCase(

    private val repository:
    UserProgressRepository

) {

    suspend operator fun invoke() {
        repository.fetchUserProgress()
    }

}