package com.everpath.data.local.mapper

import com.everpath.data.local.entity.ActivityEntity
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity

fun ActivityEntity.toDomain(): Activity {

    return Activity(
        id = id,
        title = title,
        description = description,
        status = ActivityStatus.valueOf(status)
    )
}

fun Activity.toEntity(
    goalId: String
): ActivityEntity {

    return ActivityEntity(
        id = id,
        goalId = goalId,
        title = title,
        description = description,
        status = status.name
    )
}