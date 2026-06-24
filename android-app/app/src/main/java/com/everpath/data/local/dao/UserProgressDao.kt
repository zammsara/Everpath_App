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
 */
@Dao
interface UserProgressDao {

    @Query(
        "SELECT * FROM user_progress LIMIT 1"
    )
    fun getUserProgress():
            Flow<UserProgressEntity?>


    @Query(
        "SELECT * FROM user_progress LIMIT 1"
    )
    suspend fun getCurrentUserProgress():
            UserProgressEntity?

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insertUserProgress(
        userProgress: UserProgressEntity
    )

    @Update
    suspend fun updateUserProgress(
        userProgress: UserProgressEntity
    )

}