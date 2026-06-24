package com.everpath.data.repository

import com.everpath.data.local.dao.AchievementDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.domain.model.Achievement
import com.everpath.domain.repository.AchievementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación Room del repositorio
 * de achievements.
 */
class AchievementRepositoryImpl(
    private val achievementDao: AchievementDao
) : AchievementRepository {

    override fun getAchievements():
            Flow<List<Achievement>> {

        return achievementDao
            .getAchievements()
            .map { entities ->
                entities.map {
                    it.toDomain()
                }
            }
    }

    override suspend fun getAchievementById(
        id: String
    ): Achievement? {

        return achievementDao
            .getAchievementById(id)
            ?.toDomain()
    }

    override suspend fun saveAchievement(
        achievement: Achievement
    ) {
        achievementDao.saveAchievement(
            achievement.toEntity()
        )
    }
}