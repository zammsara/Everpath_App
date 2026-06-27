package com.everpath.domain.repository

import com.everpath.domain.model.UserProgress

/**
 * Contrato encargado de definir
 * las operaciones disponibles
 * para sincronizar el progreso
 * global del usuario.
 *
 * El backend constituye la fuente
 * de verdad para la experiencia (XP),
 * mientras que Android únicamente
 * mantiene una copia local sincronizada.
 *
 * El nivel y el progreso visual
 * continúan calculándose mediante
 * los casos de uso del dominio.
 */
interface UserProgressRepository {

    /**
     * Obtiene el progreso global
     * del usuario.
     *
     * La implementación concreta
     * decidirá cuándo utilizar
     * la caché local y cuándo
     * consultar el backend.
     */
    suspend fun getUserProgress(
        userId: Long
    ): UserProgress

}