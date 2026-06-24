package com.everpath.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.LevelProgress
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSecondary
import com.everpath.ui.theme.EverpathSurface
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary
import com.everpath.ui.theme.EverpathWhite
import kotlin.math.roundToInt

@Composable
fun LevelProgressCard(
    levelProgress: LevelProgress,
    level: Int? = null
) {

    val rawProgress =
        levelProgress.progress

    val progress =
        if (rawProgress > 1f) {
            (rawProgress / 100f)
                .coerceIn(
                    0f,
                    1f
                )
        } else {
            rawProgress
                .coerceIn(
                    0f,
                    1f
                )
        }

    val percentage =
        (progress * 100f)
            .roundToInt()

    val currentXp =
        levelProgress.currentXp

    val estimatedRequiredXp =
        if (
            progress > 0f &&
            currentXp > 0
        ) {
            (currentXp / progress)
                .roundToInt()
                .coerceAtLeast(
                    currentXp
                )
        } else {
            currentXp
        }

    val remainingXp =
        (
                estimatedRequiredXp -
                        currentXp
                ).coerceAtLeast(
                0
            )

    Card(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(28.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = EverpathSurfaceSoft
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            verticalArrangement =
                Arrangement.spacedBy(14.dp)
        ) {

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(50.dp)
                            .background(
                                color =
                                    EverpathPrimary.copy(
                                        alpha = 0.14f
                                    ),
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text = "✦",
                        color = EverpathPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                }

                Spacer(
                    modifier = Modifier.size(14.dp)
                )

                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        text = "Progreso de Nivel",
                        color = EverpathTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Sigue avanzando hacia tu siguiente nivel.",
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )

                }

                Box(
                    modifier =
                        Modifier
                            .background(
                                color = EverpathPrimary,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(
                                horizontal = 12.dp,
                                vertical = 7.dp
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text =
                            if (level != null) {
                                "Nv. $level"
                            } else {
                                "Nivel"
                            },
                        color = EverpathWhite,
                        style =
                            MaterialTheme
                                .typography
                                .labelLarge,
                        fontWeight = FontWeight.Bold
                    )

                }

            }

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        text =
                            "$currentXp / $estimatedRequiredXp XP",
                        color = EverpathTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text =
                            if (remainingXp > 0) {
                                "Te faltan $remainingXp XP para avanzar."
                            } else {
                                "Ya estás listo para avanzar."
                            },
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )

                }

                Box(
                    modifier =
                        Modifier
                            .background(
                                color =
                                    EverpathSecondary.copy(
                                        alpha = 0.20f
                                    ),
                                shape = RoundedCornerShape(14.dp)
                            )
                            .padding(
                                horizontal = 10.dp,
                                vertical = 5.dp
                            )
                ) {

                    Text(
                        text = "$percentage%",
                        color = EverpathPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .labelLarge,
                        fontWeight = FontWeight.ExtraBold
                    )

                }

            }

            LinearProgressIndicator(
                progress = {
                    progress
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                color = EverpathPrimary,
                trackColor =
                    EverpathBorder.copy(
                        alpha = 0.45f
                    )
            )

            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = EverpathSurface,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .padding(12.dp)
            ) {

                Text(
                    text = "Cada punto de experiencia representa un paso más en tu camino.",
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )

            }

        }

    }

}