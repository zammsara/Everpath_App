package com.everpath.domain.usecase.achievement

import com.everpath.domain.model.Achievement

/**
 * Catálogo oficial de achievements
 * disponibles dentro de Everpath.
 *
 * Centraliza la definición de todos
 * los logros desbloqueables del sistema.
 */
object AchievementDefinitions {

    val achievements =
        listOf(

            Achievement(
                id = "ACTIVITY_1",
                title = "Primer Paso",
                description =
                    "Completa tu primera actividad.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "ACTIVITY_10",
                title = "Explorador",
                description =
                    "Completa 10 actividades.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "ACTIVITY_50",
                title = "Maestro de la Acción",
                description =
                    "Completa 50 actividades.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "GOAL_1",
                title = "Arquitecto",
                description =
                    "Completa tu primera meta.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "GOAL_10",
                title = "Constructor de Destinos",
                description =
                    "Completa 10 metas.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "XP_100",
                title = "Aprendiz",
                description =
                    "Alcanza 100 XP.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "XP_500",
                title = "Experimentado",
                description =
                    "Alcanza 500 XP.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "XP_1000",
                title = "Leyenda Emergente",
                description =
                    "Alcanza 1000 XP.",
                unlocked = false,
                unlockedAt = null
            ),

            Achievement(
                id = "LEVEL_5",
                title = "Veterano",
                description =
                    "Alcanza el nivel 5.",
                unlocked = false,
                unlockedAt = null
            )
        )
}