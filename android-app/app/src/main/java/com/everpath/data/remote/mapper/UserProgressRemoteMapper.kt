package com.everpath.data.remote.mapper

import com.everpath.data.remote.dto.progress.UserProgressResponseDto
import com.everpath.domain.model.UserProgress

/**
 * Mapper encargado de convertir
 * los DTOs remotos relacionados
 * con el progreso global del usuario
 * hacia el modelo de dominio.
 *
 * Aunque el backend devuelve información
 * adicional como nivel, progreso actual
 * y experiencia requerida para el siguiente
 * nivel, el dominio únicamente persiste la
 * experiencia (XP).
 *
 * El resto de la información no se almacena,
 * ya que puede reconstruirse en cualquier
 * momento mediante los casos de uso:
 *
 * - GetUserLevelUseCase
 * - GetLevelProgressUseCase
 *
 * De esta manera se evita duplicar estado,
 * se mantiene una única fuente de verdad
 * para el cálculo del nivel y se preserva
 * la compatibilidad con la estrategia
 * Offline First de la aplicación.
 */


fun UserProgressResponseDto.toDomain(): UserProgress {

    return UserProgress(
        xp = xp
    )
}