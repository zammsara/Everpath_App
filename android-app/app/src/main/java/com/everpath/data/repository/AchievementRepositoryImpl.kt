package com.everpath.data.repository

import android.util.Log
import com.everpath.data.local.dao.AchievementDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.AchievementRemoteDataSource
import com.everpath.data.remote.mapper.toDomain
import com.everpath.data.remote.util.safeApiCall
import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Repositorio híbrido encargado de sincronizar
 * achievements entre el backend y Room.
 *
 * Los achievements son calculados exclusivamente
 * por el backend y almacenados localmente para
 * soportar la estrategia Offline First.
 */
class AchievementRepositoryImpl(
    private val achievementDao: AchievementDao,
    private val achievementRemoteDataSource:
    AchievementRemoteDataSource
) : AchievementRepository {

    /**
     * Obtiene los achievements del usuario.
     *
     * Este método se mantiene únicamente por
     * compatibilidad mientras la aplicación
     * migra completamente al patrón
     * observe + fetch.
     */
    override suspend fun getAchievementsByUser(
        userId: Long
    ): List<Achievement> {

        fetchAchievements(userId)

        return observeAchievements()
            .first()
    }

    /**
     * Sincroniza los achievements del usuario
     * desde el backend hacia Room.
     */
    override suspend fun fetchAchievements(
        userId: Long
    ) {

        val result =
            safeApiCall(
                tag = "AchievementRepository"
            ) {

                achievementRemoteDataSource
                    .getAchievements(
                        userId
                    )

            }

        val remoteAchievements =
            result.getOrElse {

                Log.i(
                    "AchievementRepository",
                    "No fue posible sincronizar los achievements. Se utilizará la información almacenada localmente."
                )

                return
            }

        remoteAchievements.forEach {

            achievementDao.saveAchievement(

                it.toDomain()
                    .toEntity()
            )
        }
    }

    /**
     * Observa continuamente los achievements
     * almacenados localmente en Room.
     *
     * La UI consume exclusivamente este Flow
     * para mantenerse sincronizada automáticamente
     * con los cambios realizados sobre la base
     * de datos local.
     */
    override fun observeAchievements():
            Flow<List<Achievement>> {

        return achievementDao
            .getAchievements()
            .map { achievements ->

                achievements.map {
                    it.toDomain()
                }
            }
    }

    override suspend fun getAchievementById(
        achievementId: String
    ): Achievement? {

        return achievementDao
            .getAchievementById(
                achievementId
            )
            ?.toDomain()
    }
}