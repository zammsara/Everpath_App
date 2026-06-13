package com.everpath.data.local.mapper

import com.everpath.data.local.entity.GoalEntity
import com.everpath.data.local.relation.GoalWithActivities
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode

fun GoalWithActivities.toDomain(): GoalNode {

    return GoalNode(
        id = goal.id,
        title = goal.title,
        description = goal.description,
        lifeArea = LifeAreaType.valueOf(goal.lifeArea),
        status = GoalStatus.valueOf(goal.status),
        activities = activities.map {
            it.toDomain()
        }
    )
}

fun GoalNode.toEntity(): GoalEntity {

    return GoalEntity(
        id = id,
        title = title,
        description = description,
        lifeArea = lifeArea.name,
        status = status.name
    )
}