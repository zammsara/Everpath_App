package com.everpath.data.repository

import com.everpath.data.local.dao.UserProgressDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.UserProgressRemoteDataSource
import com.everpath.data.remote.mapper.toDomain
import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación híbrida del repositorio
 * de progreso del usuario.
 *
 * Sigue una estrategia Offline First:
 *
 * 1. Consulta Room.
 * 2. Si no existe información, consulta
 *    el backend.
 * 3. Actualiza la caché local.
 * 4. Devuelve el modelo de dominio.
 */
class UserProgressRepositoryImpl(

    private val userProgressDao: UserProgressDao,
    private val remoteDataSource: UserProgressRemoteDataSource

) : UserProgressRepository {

    /**
     * Observa continuamente el progreso
     * almacenado localmente.
     */
    override fun observeUserProgress():
            Flow<UserProgress?> {

        return userProgressDao
            .getUserProgress()
            .map { progress ->

                progress?.toDomain()

            }
    }

    /**
     * Obtiene el progreso desde el backend
     * y actualiza la copia almacenada en Room.
     */
    override suspend fun fetchUserProgress(
        userId: Long
    ) {

        val remoteProgress =
            remoteDataSource
                .getUserProgress(
                    userId
                )

        userProgressDao.upsertUserProgress(

            remoteProgress
                .toDomain()
                .toEntity()
        )
    }

}