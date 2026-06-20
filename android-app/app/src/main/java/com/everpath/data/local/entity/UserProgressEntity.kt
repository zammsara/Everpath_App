package com.everpath.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad Room encargada de
 * persistir el progreso global
 * del usuario.
 */
@Entity(
    tableName = "user_progress"
)
data class UserProgressEntity(

    @PrimaryKey
    val id: Int = 1,

    val xp: Int

)