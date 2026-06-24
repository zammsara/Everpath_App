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
        "SELECT * FROM achievements"
    )
    fun getAchievements():
            Flow<List<AchievementEntity>>

    @Query(
        "SELECT * FROM achievements WHERE id = :id"
    )
    suspend fun getAchievementById(
        id: String
    ): AchievementEntity?

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun saveAchievement(
        achievement: AchievementEntity
    )
}