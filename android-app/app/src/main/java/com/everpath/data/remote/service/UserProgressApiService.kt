package com.everpath.data.remote.service

import com.everpath.data.remote.dto.progress.UserProgressResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Servicio Retrofit encargado de declarar
 * las operaciones REST relacionadas con
 * el progreso global del usuario.
 *
 * Esta interfaz representa el contrato
 * entre Android y el backend de Everpath.
 */
interface UserProgressApiService {


    @GET("api/v1/progress/{userId}")
    suspend fun getUserProgress(

        @Path("userId")
        userId: Long

    ): UserProgressResponseDto

}