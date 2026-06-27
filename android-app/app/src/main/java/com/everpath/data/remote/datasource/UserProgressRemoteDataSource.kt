package com.everpath.data.remote.datasource

import com.everpath.data.remote.dto.progress.UserProgressResponseDto
import com.everpath.data.remote.network.RetrofitProvider
import com.everpath.data.remote.service.UserProgressApiService

/**
 * DataSource remoto encargado de centralizar
 * todas las operaciones relacionadas con
 * el progreso del usuario mediante Retrofit.
 *
 * Esta clase encapsula el acceso al
 * UserProgressApiService para evitar que
 * otras capas dependan directamente de
 * Retrofit.
 */
class UserProgressRemoteDataSource {


    private val apiService: UserProgressApiService =
        RetrofitProvider
            .retrofit
            .create(
                UserProgressApiService::class.java
            )


    suspend fun getUserProgress(
        userId: Long
    ): UserProgressResponseDto {

        return apiService.getUserProgress(
            userId
        )
    }

}