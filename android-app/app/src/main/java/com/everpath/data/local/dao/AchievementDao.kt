package com.everpath.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everpath.data.local.entity.AchievementEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO encargado de gestionar
 * la persistencia de achievements.
 */
@Dao
interface AchievementDao {

    @Query(
        "SELECT * FROM achievements " +
                "WHERE userId = :userId"
    )
    fun getAchievements(userId: Long):
            Flow<List<AchievementEntity>>

    @Query(
        "SELECT * FROM achievements " +
                "WHERE id = :id " +
                "AND userId = :userId"
    )
    suspend fun getAchievementById(
        id: String,
        userId: Long
    ): AchievementEntity?

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun saveAchievement(
        achievement: AchievementEntity
    )
}