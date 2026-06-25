package com.everpath.presentation.today.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.domain.model.GoalNode
import com.everpath.ui.theme.EverpathAccent
import com.everpath.ui.theme.EverpathSurface
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary

@Composable
fun GoalSummaryCard(
    goals: List<GoalNode>
) {

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
                            .size(58.dp)
                            .background(
                                color =
                                    EverpathAccent.copy(
                                        alpha = 0.16f
                                    ),
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id = R.drawable.ic_dashboard_star
                            ),
                        contentDescription = "Metas activas",
                        modifier =
                            Modifier.size(40.dp),
                        contentScale =
                            ContentScale.Fit
                    )

                }

                Spacer(
                    modifier =
                        Modifier.size(14.dp)
                )

                Column {

                    Text(
                        text = "Metas Activas",
                        color = EverpathTextPrimary,
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = "Lo que estás construyendo ahora.",
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )

                }

            }

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                goals
                    .take(4)
                    .forEach { goal ->

                        GoalLine(
                            title = goal.title
                        )

                    }

                if (
                    goals.size > 4
                ) {

                    Text(
                        text = "+${goals.size - 4} metas más",
                        color = EverpathTextSecondary,
                        style =
                            MaterialTheme
                                .typography
                                .bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )

                }

            }

        }

    }

}

@Composable
private fun GoalLine(
    title: String
) {

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = EverpathSurface,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 10.dp
                ),
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Box(
            modifier =
                Modifier
                    .size(28.dp)
                    .background(
                        color =
                            EverpathAccent.copy(
                                alpha = 0.13f
                            ),
                        shape = CircleShape
                    ),
            contentAlignment =
                Alignment.Center
        ) {

            Image(
                painter =
                    painterResource(
                        id = R.drawable.ic_dashboard_star
                    ),
                contentDescription = "Meta activa",
                modifier =
                    Modifier.size(18.dp),
                contentScale =
                    ContentScale.Fit
            )

        }

        Spacer(
            modifier =
                Modifier.size(10.dp)
        )

        Text(
            text = title,
            color = EverpathTextPrimary,
            style =
                MaterialTheme
                    .typography
                    .bodyMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }

}