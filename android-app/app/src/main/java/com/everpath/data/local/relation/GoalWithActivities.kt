package com.everpath.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.everpath.data.local.entity.ActivityEntity
import com.everpath.data.local.entity.GoalEntity

data class GoalWithActivities(

    @Embedded
    val goal: GoalEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "goalId"
    )
    val activities: List<ActivityEntity>
)