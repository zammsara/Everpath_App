package com.everpath.domain.repository

import com.everpath.domain.model.UserProgress
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio encargado de
 * gestionar el progreso global
 * del usuario.
 */
interface UserProgressRepository {

    fun getUserProgress():
            Flow<UserProgress?>

    suspend fun saveUserProgress(
        userProgress: UserProgress
    )

    suspend fun updateUserProgress(
        userProgress: UserProgress
    )

}