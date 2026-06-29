package com.everpath.data.local.mapper

import android.R.attr.id
import com.everpath.data.local.entity.UserProgressEntity
import com.everpath.data.session.UserSession
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
        userId = UserSession.userId,
        xp = xp
    )

}