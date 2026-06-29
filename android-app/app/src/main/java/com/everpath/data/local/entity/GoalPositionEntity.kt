package com.everpath.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "goal_positions",

    foreignKeys = [
        ForeignKey(
            entity = GoalEntity::class,
            parentColumns = ["id"],
            childColumns = ["goalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    indices = [
        Index(value = ["goalId"])
    ]
)
data class GoalPositionEntity(

    @PrimaryKey
    val goalId: String,

    val userId: Long,

    val x: Float,

    val y: Float
)