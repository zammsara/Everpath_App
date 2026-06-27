package com.everpath.domain.repository

import com.everpath.domain.model.UserProgress

/**
 * Contrato encargado de definir
 * todas las operaciones relacionadas
 * con el progreso global del usuario.
 *
 * El dominio únicamente conoce
 * las operaciones disponibles,
 * sin depender de la fuente
 * de datos utilizada para obtenerlas.
 *
 * La implementación concreta será
 * responsable de coordinar Room
 * y el backend mediante Retrofit.
 */
interface UserProgressRepository {

    suspend fun getUserProgress(
        userId: Long
    ): UserProgress

    /**
     * Actualiza el progreso
     * del usuario en el backend
     * y sincroniza la información
     * local cuando sea necesario.
     */
    suspend fun updateUserProgress(
        userProgress: UserProgress
    ): UserProgress

}