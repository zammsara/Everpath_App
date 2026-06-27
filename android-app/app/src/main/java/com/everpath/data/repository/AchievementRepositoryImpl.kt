package com.everpath.data.repository

import com.everpath.data.local.dao.AchievementDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.data.remote.datasource.AchievementRemoteDataSource
import com.everpath.data.remote.mapper.toDomain
import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.first

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

    override suspend fun getAchievementsByUser(
        userId: Long
    ): List<Achievement> {

        return try {

            val remoteAchievements =
                achievementRemoteDataSource
                    .getAchievements(
                        userId
                    )

            remoteAchievements.forEach {
                achievementDao.saveAchievement(
                    it.toDomain()
                        .toEntity()
                )
            }

            remoteAchievements.map {
                it.toDomain()
            }

        } catch (
            exception: Exception
        ) {

            achievementDao
                .getAchievements()
                .first()
                .map {
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