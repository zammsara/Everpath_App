package com.everpath.data.remote.service

import com.everpath.data.remote.dto.progress.AchievementResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Servicio Retrofit encargado de declarar
 * las operaciones REST relacionadas con
 * los achievements del usuario.
 *
 * Esta interfaz representa el contrato
 * entre Android y el backend de Everpath.
 */
interface AchievementApiService {

    /**
     * Obtiene todos los achievements
     * desbloqueados por un usuario.
     */
    @GET("api/v1/progress/{userId}/achievements")
    suspend fun getAchievements(

        @Path("userId")
        userId: Long

    ): List<AchievementResponseDto>

}