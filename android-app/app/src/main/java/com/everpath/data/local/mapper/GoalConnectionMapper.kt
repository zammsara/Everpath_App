package com.everpath.data.local.mapper

import com.everpath.data.local.entity.GoalConnectionEntity
import com.everpath.domain.model.GoalConnection

/**
 * Mappers encargados de convertir GoalConnectionEntity
 * a GoalConnection de dominio y viceversa.
 */
fun GoalConnectionEntity.toDomain(): GoalConnection {

    return GoalConnection(
        id = id,
        sourceGoalId = sourceGoalId,
        targetGoalId = targetGoalId
    )
}

fun GoalConnection.toEntity(): GoalConnectionEntity {

    return GoalConnectionEntity(
        id = id,
        sourceGoalId = sourceGoalId,
        targetGoalId = targetGoalId
    )
}