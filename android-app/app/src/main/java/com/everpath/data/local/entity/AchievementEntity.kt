package com.everpath.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad Room encargada de
 * persistir los logros desbloqueados
 * por el usuario.
 */
@Entity(
    tableName = "achievements"
)
data class AchievementEntity(

    @PrimaryKey
    val id: String,

    val userId: Long,

    val title: String,

    val description: String,

    val unlocked: Boolean,

    val unlockedAt: Long?

)