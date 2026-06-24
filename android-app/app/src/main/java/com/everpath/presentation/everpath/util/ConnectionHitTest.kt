package com.everpath.presentation.everpath.util

import androidx.compose.ui.geometry.Offset
import kotlin.math.sqrt

/**
 * Utilidad encargada de determinar si un toque
 * ocurrió cerca de una línea de conexión.
 */
object ConnectionHitTest {

    fun isPointNearLine(
        point: Offset,
        lineStart: Offset,
        lineEnd: Offset,
        tolerance: Float = 40f
    ): Boolean {

        val lineLengthSquared =
            (
                    (lineEnd.x - lineStart.x) *
                            (lineEnd.x - lineStart.x)
                    ) +
                    (
                            (lineEnd.y - lineStart.y) *
                                    (lineEnd.y - lineStart.y)
                            )

        if (lineLengthSquared == 0f) {
            return false
        }

        val projection =
            (
                    (
                            (point.x - lineStart.x) *
                                    (lineEnd.x - lineStart.x)
                            ) +
                            (
                                    (point.y - lineStart.y) *
                                            (lineEnd.y - lineStart.y)
                                    )
                    ) / lineLengthSquared

        val clampedProjection =
            projection.coerceIn(
                0f,
                1f
            )

        val closestPoint =
            Offset(
                x =
                    lineStart.x +
                            (
                                    lineEnd.x -
                                            lineStart.x
                                    ) *
                            clampedProjection,

                y =
                    lineStart.y +
                            (
                                    lineEnd.y -
                                            lineStart.y
                                    ) *
                            clampedProjection
            )

        val distance =
            sqrt(
                (
                        point.x -
                                closestPoint.x
                        ) *
                        (
                                point.x -
                                        closestPoint.x
                                ) +
                        (
                                point.y -
                                        closestPoint.y
                                ) *
                        (
                                point.y -
                                        closestPoint.y
                                )
            )

        return distance <= tolerance
    }

}