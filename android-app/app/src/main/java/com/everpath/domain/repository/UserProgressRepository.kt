package com.everpath.domain.repository

import com.everpath.domain.model.UserProgress
import kotlinx.coroutines.flow.Flow

/**
 * Contrato encargado de sincronizar el
 * progreso global del usuario siguiendo
 * la estrategia Offline First.
 *
 * El backend representa la fuente oficial
 * de verdad para el progreso.
 *
 * Room mantiene una copia sincronizada
 * utilizada por la interfaz mediante Flow.
 *
 * La sincronización remota se realiza
 * mediante operaciones fetch.
 */

interface UserProgressRepository {

    /**
     * Observa continuamente el progreso
     * almacenado en Room.
     */
    fun observeUserProgress():
            Flow<UserProgress?>

    /**
     * Sincroniza el progreso del usuario
     * actualmente autenticado desde el backend
     * hacia Room.
     *
     * La implementación concreta será la encargada
     * de resolver qué usuario se encuentra activo.
     */
    suspend fun fetchUserProgress()

}