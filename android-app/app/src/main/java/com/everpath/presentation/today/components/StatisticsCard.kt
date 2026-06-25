package com.everpath.presentation.today.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import com.everpath.ui.theme.EverpathAccent
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSecondary
import com.everpath.ui.theme.EverpathSurface
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathSuccess
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StatisticsCard(
    goalCount: Int,
    completedGoalCount: Int,
    activityCount: Int,
    completedActivityCount: Int
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
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
                    .padding(20.dp)
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
                                id = R.drawable.ic_activities
                            ),
                        contentDescription = "Resumen general",
                        modifier = Modifier.size(40.dp),
                        contentScale = ContentScale.Fit
                    )

                }

                Spacer(
                    modifier = Modifier.size(14.dp)
                )

                Column {

                    Text(
                        text = "Resumen General",
                        color = EverpathTextPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Tu progreso convertido en números.",
                        color = EverpathTextSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )

                }

            }

            Spacer(
                modifier = Modifier.size(18.dp)
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                StatChip(
                    iconRes = R.drawable.ic_goal_target,
                    label = "Metas",
                    value = goalCount,
                    color = EverpathPrimary
                )

                StatChip(
                    iconRes = R.drawable.ic_activity_completed,
                    label = "Metas completadas",
                    value = completedGoalCount,
                    color = EverpathSuccess
                )

                StatChip(
                    iconRes = R.drawable.ic_activities,
                    label = "Actividades",
                    value = activityCount,
                    color = EverpathSecondary
                )

                StatChip(
                    iconRes = R.drawable.ic_activity_completed,
                    label = "Actividades completadas",
                    value = completedActivityCount,
                    color = EverpathAccent
                )

            }

        }

    }

}

@Composable
private fun StatChip(
    iconRes: Int,
    label: String,
    value: Int,
    color: Color
) {

    Row(
        modifier =
            Modifier
                .background(
                    color = EverpathSurface,
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 10.dp
                ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier =
                Modifier
                    .size(34.dp)
                    .background(
                        color = color.copy(alpha = 0.13f),
                        shape = CircleShape
                    ),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter =
                    painterResource(
                        id = iconRes
                    ),
                contentDescription = label,
                modifier = Modifier.size(22.dp),
                contentScale = ContentScale.Fit
            )

        }

        Spacer(
            modifier = Modifier.size(8.dp)
        )

        Column {

            Text(
                text = value.toString(),
                color = EverpathTextPrimary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = label,
                color = EverpathTextSecondary,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold
            )

        }

    }

}