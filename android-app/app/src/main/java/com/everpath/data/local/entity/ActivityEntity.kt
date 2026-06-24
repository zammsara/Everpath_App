package com.everpath.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidad Room que representa una actividad asociada
 * a una meta dentro del sistema Everpath.
 */
@Entity(
    tableName = "activities",
    foreignKeys = [
        ForeignKey(
            entity = GoalEntity::class,
            parentColumns = ["id"],
            childColumns = ["goalId"],
            onDelete = ForeignKey.CASCADE //si se elimina un goal, se eliminan sus actividades asociadas
        )
    ],
    indices = [
        Index(value = ["goalId"])
    ]
)
data class ActivityEntity(

    @PrimaryKey
    val id: String,

    val goalId: String,

    val title: String,

    val description: String,

    val status: String,

    val xpGranted: Boolean
)