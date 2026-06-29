package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.everpath.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO encargado de gestionar
 * la persistencia del progreso
 * global del usuario.
 *
 * Proporciona operaciones utilizadas
 * por el repositorio híbrido para
 * mantener sincronizada la caché
 * local con el backend.
 */
@Dao
interface UserProgressDao {

    @Query(
        "SELECT * FROM user_progress " +
                "WHERE userId = :userId " +
                "LIMIT 1"
    )
    fun getUserProgress(userId: Long):
            Flow<UserProgressEntity?>

    @Query(
        "SELECT * FROM user_progress " +
                "WHERE userId = :userId " +
                "LIMIT 1"
    )
    suspend fun getCurrentUserProgress(userId: Long):
            UserProgressEntity?

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insertUserProgress(
        userProgress: UserProgressEntity
    )

    /**
     * Inserta o reemplaza el progreso
     * sincronizado desde el backend.
     */
    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun upsertUserProgress(
        userProgress: UserProgressEntity
    )

    @Update
    suspend fun updateUserProgress(
        userProgress: UserProgressEntity
    )

}