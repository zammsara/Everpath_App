package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement

/**
 * Caso de uso encargado de exponer
 * el catálogo oficial de achievements
 * disponibles dentro de Everpath.
 *
 * Actualmente devuelve todas las
 * definiciones registradas en
 * AchievementDefinitions.
 */
class GetAchievementsUseCase {

    operator fun invoke():
            List<Achievement> {

        return AchievementDefinitions
            .achievements
    }
}