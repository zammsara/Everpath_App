package com.everpath.data.local.mapper

import com.everpath.data.local.entity.UserProgressEntity
import com.everpath.domain.model.UserProgress

/**
 * Mappers entre Room y dominio
 * para UserProgress.
 */
fun UserProgressEntity.toDomain(): UserProgress {

    return UserProgress(
        id = id,
        xp = xp
    )

}

fun UserProgress.toEntity():
        UserProgressEntity {

    return UserProgressEntity(
        id = id,
        xp = xp
    )

}