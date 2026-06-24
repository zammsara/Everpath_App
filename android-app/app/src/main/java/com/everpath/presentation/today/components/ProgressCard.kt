package com.everpath.presentation.today.components

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
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathSuccess
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary
import kotlin.math.roundToInt

@Composable
fun ProgressCard(
    progress: Float
) {

    val safeProgress =
        progress.coerceIn(
            0f,
            1f
        )

    val percent =
        (safeProgress * 100f)
            .roundToInt()

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
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(48.dp)
                            .background(
                                color =
                                    EverpathSuccess.copy(
                                        alpha = 0.14f
                                    ),
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text = "✓",
                        color = EverpathSuccess,
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
                        text = "Progreso General",
                        color = EverpathTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Avance total de tus metas.",
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )

                }

                Text(
                    text = "$percent%",
                    color = EverpathSuccess,
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )

            }

            LinearProgressIndicator(
                progress = {
                    safeProgress
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(9.dp),
                color = EverpathSuccess,
                trackColor =
                    EverpathBorder.copy(
                        alpha = 0.45f
                    )
            )

        }

    }

}