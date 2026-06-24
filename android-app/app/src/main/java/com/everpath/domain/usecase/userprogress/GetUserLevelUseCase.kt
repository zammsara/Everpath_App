package com.everpath.domain.usecase.userprogress

/**
 * Caso de uso encargado de calcular
 * el nivel actual del usuario a partir
 * de la experiencia acumulada.
 *
 * El nivel no se persiste en base de datos.
 * Siempre se calcula utilizando la XP actual.
 */
class GetUserLevelUseCase {

    operator fun invoke(
        xp: Int
    ): Int {

        return when {

            xp >= 1000 -> 5

            xp >= 500 -> 4

            xp >= 250 -> 3

            xp >= 100 -> 2

            else -> 1
        }
    }
}