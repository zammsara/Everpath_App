package com.everpath.domain.usecase.userprogress

import com.everpath.domain.model.LevelProgress

/**
 * Caso de uso encargado de calcular
 * el progreso actual del usuario
 * dentro de su nivel.
 *
 * Toda la información se deriva
 * exclusivamente de la XP acumulada.
 */
class GetLevelProgressUseCase {

    operator fun invoke(
        xp: Int
    ): LevelProgress {

        return when {

            xp >= 1000 -> {

                LevelProgress(
                    currentLevel = 5,
                    currentXp = xp,
                    currentLevelXp = xp - 1000,
                    requiredXpForNextLevel = 1000,
                    progress = 1f
                )
            }

            xp >= 500 -> {

                val levelXp =
                    xp - 500

                val requiredXp =
                    1000 - 500

                LevelProgress(
                    currentLevel = 4,
                    currentXp = xp,
                    currentLevelXp = levelXp,
                    requiredXpForNextLevel = requiredXp,
                    progress =
                        levelXp.toFloat() /
                                requiredXp.toFloat()
                )
            }

            xp >= 250 -> {

                val levelXp =
                    xp - 250

                val requiredXp =
                    500 - 250

                LevelProgress(
                    currentLevel = 3,
                    currentXp = xp,
                    currentLevelXp = levelXp,
                    requiredXpForNextLevel = requiredXp,
                    progress =
                        levelXp.toFloat() /
                                requiredXp.toFloat()
                )
            }

            xp >= 100 -> {

                val levelXp =
                    xp - 100

                val requiredXp =
                    250 - 100

                LevelProgress(
                    currentLevel = 2,
                    currentXp = xp,
                    currentLevelXp = levelXp,
                    requiredXpForNextLevel = requiredXp,
                    progress =
                        levelXp.toFloat() /
                                requiredXp.toFloat()
                )
            }

            else -> {

                val requiredXp =
                    100

                LevelProgress(
                    currentLevel = 1,
                    currentXp = xp,
                    currentLevelXp = xp,
                    requiredXpForNextLevel = requiredXp,
                    progress =
                        xp.toFloat() /
                                requiredXp.toFloat()
                )
            }
        }
    }
}