package com.everpath.data.repository

import com.everpath.data.local.dao.UserProgressDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.UserProgressRemoteDataSource
import com.everpath.data.remote.mapper.toDomain
import com.everpath.data.session.UserSession
import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import android.util.Log
import com.everpath.data.remote.util.safeApiCall

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
     * Sincroniza el progreso del usuario
     * desde el backend hacia Room.
     *
     * Si la sincronización falla, se conserva
     * la información almacenada localmente
     * para mantener el funcionamiento
     * Offline First.
     */
    override suspend fun fetchUserProgress() {
        val result =
            safeApiCall(
                tag = "UserProgressRepository"
            ) {

                remoteDataSource
                    .getUserProgress(
                        UserSession.userId
                    )
            }

        val remoteProgress =
            result.getOrElse {
                Log.i(
                    "UserProgressRepository",
                    "Sincronización omitida. Se utilizará el progreso almacenado localmente."
                )

                return
            }

        userProgressDao.upsertUserProgress(
            remoteProgress
                .toDomain()
                .toEntity()
        )
    }

}