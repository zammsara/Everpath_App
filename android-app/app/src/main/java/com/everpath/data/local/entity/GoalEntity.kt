package com.everpath.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_nodes")
data class GoalEntity(

    @PrimaryKey
    val id: String,

    val userId: Long,

    val title: String,

    val description: String,

    val lifeArea: String,

    val status: String,

    val xpGranted: Boolean
)