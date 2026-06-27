package com.everpath.data.local.mapper

import com.everpath.data.local.entity.ActivityEntity
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity

/**
 * Mappers encargados de convertir ActivityEntity
 * a Activity de dominio y viceversa.
 */
fun ActivityEntity.toDomain(): Activity {

    return Activity(
        id = id,
        goalId = goalId,
        title = title,
        description = description,
        status = ActivityStatus.valueOf(status),
        xpGranted = xpGranted
    )
}

fun Activity.toEntity(): ActivityEntity {

    return ActivityEntity(
        id = id,
        goalId = goalId,
        title = title,
        description = description,
        status = status.name,
        xpGranted = xpGranted
    )
}