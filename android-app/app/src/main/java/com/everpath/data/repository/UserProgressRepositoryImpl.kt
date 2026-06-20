package com.everpath.data.repository

import com.everpath.data.local.dao.UserProgressDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.domain.model.UserProgress
import com.everpath.domain.repository.UserProgressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación Room del
 * repositorio UserProgress.
 */
class UserProgressRepositoryImpl(
    private val userProgressDao: UserProgressDao
) : UserProgressRepository {

    override fun getUserProgress():
            Flow<UserProgress?> {

        return userProgressDao
            .getUserProgress()
            .map { entity ->
                entity?.toDomain()
            }
    }

    override suspend fun
            getCurrentUserProgress():
            UserProgress? {

        return userProgressDao
            .getCurrentUserProgress()
            ?.toDomain()
    }

    override suspend fun saveUserProgress(
        userProgress: UserProgress
    ) {
        userProgressDao.insertUserProgress(
            userProgress.toEntity()
        )
    }

    override suspend fun updateUserProgress(
        userProgress: UserProgress
    ) {
        userProgressDao.updateUserProgress(
            userProgress.toEntity()
        )
    }
}