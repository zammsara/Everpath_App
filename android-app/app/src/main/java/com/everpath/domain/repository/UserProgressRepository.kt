package com.everpath.domain.repository

import com.everpath.domain.model.UserProgress
import kotlinx.coroutines.flow.Flow

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
     * Observa continuamente el progreso
     * almacenado en Room.
     */
    fun observeUserProgress():
            Flow<UserProgress?>

}