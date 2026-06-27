package com.everpath.data.remote.datasource

import com.everpath.data.remote.dto.progress.AchievementResponseDto
import com.everpath.data.remote.network.RetrofitProvider
import com.everpath.data.remote.service.AchievementApiService

/**
 * DataSource remoto encargado de centralizar
 * todas las operaciones relacionadas con
 * los achievements mediante Retrofit.
 *
 * Esta clase encapsula completamente
 * el acceso al AchievementApiService
 * evitando dependencias directas
 * hacia Retrofit desde otras capas.
 */
class AchievementRemoteDataSource {


    private val apiService =
        RetrofitProvider
            .retrofit
            .create(
                AchievementApiService::class.java
            )


    suspend fun getAchievements(
        userId: Long
    ): List<AchievementResponseDto> {

        return apiService.getAchievements(
            userId
        )
    }

}