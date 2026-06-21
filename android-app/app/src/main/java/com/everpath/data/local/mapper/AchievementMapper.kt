package com.everpath.data.local.mapper

import com.everpath.data.local.entity.AchievementEntity
import com.everpath.domain.model.Achievement

/**
 * Mapper entre AchievementEntity
 * y Achievement de dominio.
 */
fun AchievementEntity.toDomain():
        Achievement {

    return Achievement(
        id = id,
        title = title,
        description = description,
        unlocked = unlocked,
        unlockedAt = unlockedAt
    )
}

fun Achievement.toEntity():
        AchievementEntity {

    return AchievementEntity(
        id = id,
        title = title,
        description = description,
        unlocked = unlocked,
        unlockedAt = unlockedAt
    )
}