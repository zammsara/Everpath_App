package com.everpath.presentation.components

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.domain.model.LevelProgress
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSurface
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary
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
            (rawProgress / 100f).coerceIn(0f, 1f)
        } else {
            rawProgress.coerceIn(0f, 1f)
        }

    val percentage =
        (progress * 100f).roundToInt()

    val currentXp =
        levelProgress.currentXp

    val estimatedRequiredXp =
        if (progress > 0f && currentXp > 0) {
            (currentXp / progress)
                .roundToInt()
                .coerceAtLeast(currentXp)
        } else {
            currentXp
        }

    val remainingXp =
        (estimatedRequiredXp - currentXp)
            .coerceAtLeast(0)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = EverpathSurfaceSoft
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(58.dp)
                            .background(
                                color = EverpathPrimary.copy(alpha = 0.12f),
                                shape = CircleShape
                            ),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id = R.drawable.ic_achievement_trophy
                            ),
                        contentDescription = "Progreso de nivel",
                        modifier = Modifier.size(38.dp),
                        contentScale = ContentScale.Fit
                    )

                }

                Spacer(
                    modifier = Modifier.size(14.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Progreso de Nivel",
                        color = EverpathTextPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Sigue avanzando hacia tu siguiente nivel.",
                        color = EverpathTextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )

                }

                Surface(
                    color = EverpathPrimary,
                    shape = RoundedCornerShape(22.dp)
                ) {

                    Text(
                        text =
                            if (level != null) {
                                "Nivel $level"
                            } else {
                                "Nivel"
                            },
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        modifier =
                            Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            )
                    )

                }

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "$currentXp / $estimatedRequiredXp XP",
                        color = EverpathTextPrimary,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Te faltan $remainingXp XP para avanzar.",
                        color = EverpathTextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )

                }

                Box(
                    modifier =
                        Modifier
                            .background(
                                color = EverpathPrimary.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(
                                horizontal = 12.dp,
                                vertical = 7.dp
                            )
                ) {

                    Text(
                        text = "$percentage%",
                        color = EverpathPrimary,
                        style = MaterialTheme.typography.labelLarge,
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
                    EverpathBorder.copy(alpha = 0.45f)
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = EverpathSurface,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(42.dp)
                            .background(
                                color = EverpathPrimary.copy(alpha = 0.12f),
                                shape = CircleShape
                            ),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id = R.drawable.ic_progress
                            ),
                        contentDescription = "Experiencia",
                        modifier = Modifier.size(26.dp),
                        contentScale = ContentScale.Fit
                    )

                }

                Spacer(
                    modifier = Modifier.size(12.dp)
                )

                Text(
                    text = "Cada punto de experiencia representa un paso más en tu camino.",
                    color = EverpathTextSecondary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )

            }

        }

    }

}