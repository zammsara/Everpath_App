package com.everpath.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entidad Room que representa una conexión persistente
 * entre dos metas dentro del grafo de progreso de Everpath.
 *
 * sourceGoalId → targetGoalId
 */
@Entity(
    tableName = "goal_connections",
    foreignKeys = [
        ForeignKey(
            entity = GoalEntity::class,
            parentColumns = ["id"],
            childColumns = ["sourceGoalId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GoalEntity::class,
            parentColumns = ["id"],
            childColumns = ["targetGoalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["sourceGoalId"]),
        Index(value = ["targetGoalId"])
    ]
)
data class GoalConnectionEntity(

    @PrimaryKey
    val id: String,

    val userId: Long,

    val sourceGoalId: String,

    val targetGoalId: String
)