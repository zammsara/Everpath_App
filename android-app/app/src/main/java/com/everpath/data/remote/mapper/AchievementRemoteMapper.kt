package com.everpath.data.remote.mapper

import com.everpath.data.remote.dto.progress.AchievementResponseDto
import com.everpath.domain.model.Achievement
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/**
 * Mapper encargado de convertir los DTO remotos
 * relacionados con achievements hacia los modelos
 * utilizados por la capa de dominio.
 *
 * Toda la lógica de conversión entre el contrato
 * REST y el dominio permanece encapsulada aquí.
 *
 * El backend expone la fecha de desbloqueo como
 * un String en formato ISO-8601, mientras que el
 * dominio trabaja con un timestamp (Long) para
 * facilitar su persistencia mediante Room y su
 * utilización dentro de la aplicación.
 */
fun AchievementResponseDto.toDomain(): Achievement {

    return Achievement(

        id = id,

        title = title,

        description = description,

        unlocked = unlocked,

        unlockedAt = parseUnlockedAt(unlockedAt)

    )
}

/**
 * Convierte una fecha en formato ISO-8601
 * enviada por el backend hacia un timestamp
 * representado en milisegundos.
 *
 * Si la fecha es nula o presenta un formato
 * inválido, se devuelve null para evitar
 * propagar excepciones hacia capas superiores.
 */
private fun parseUnlockedAt(
    unlockedAt: String?
): Long? {

    if (unlockedAt == null) {
        return null
    }

    return try {

        SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.US
        ).apply {

            timeZone = TimeZone.getTimeZone("UTC")

        }.parse(unlockedAt)?.time

    } catch (
        exception: Exception
    ) {

        null

    }
}