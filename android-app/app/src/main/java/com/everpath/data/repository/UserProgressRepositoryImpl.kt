package com.everpath.data.repository

import com.everpath.data.local.dao.UserProgressDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.UserProgressRemoteDataSource
import com.everpath.data.remote.mapper.toDomain
import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository

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

    private val remoteDataSource:
    UserProgressRemoteDataSource

) : UserProgressRepository {

    override suspend fun getUserProgress(
        userId: Long
    ): UserProgress {

        userProgressDao
            .getCurrentUserProgress()
            ?.let {

                return it.toDomain()

            }

        val remoteProgress =

            remoteDataSource
                .getUserProgress(
                    userId
                )

        val domainProgress =

            remoteProgress
                .toDomain()

        userProgressDao.upsertUserProgress(

            domainProgress.toEntity()

        )

        return domainProgress

    }

}