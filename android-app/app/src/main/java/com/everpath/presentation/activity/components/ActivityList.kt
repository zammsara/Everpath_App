package com.everpath.presentation.activity.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.domain.enums.ActivityStatus
import com.everpath.domain.model.Activity
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathStatusCompleted
import com.everpath.ui.theme.EverpathStatusCompletedContainer
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary

@Composable
fun ActivityList(
    activities: List<Activity>,
    onActivityClick: (String) -> Unit
) {

    Column(
        verticalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        activities.forEach { activity ->

            ActivityListCard(
                activity = activity,
                onClick = {
                    onActivityClick(
                        activity.id
                    )
                }
            )

        }

    }

}

@Composable
private fun ActivityListCard(
    activity: Activity,
    onClick: () -> Unit
) {

    val statusColor =
        activity.status.toActivityColor()

    val containerColor =
        activity.status.toActivityContainerColor()

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(24.dp),
                    clip = false
                )
                .border(
                    width = 1.dp,
                    color =
                        statusColor.copy(
                            alpha = 0.20f
                        ),
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable {
                    onClick()
                },
        shape = RoundedCornerShape(24.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = containerColor
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .heightIn(
                        min = 96.dp
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 14.dp
                    ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = activity.title,
                    color = EverpathTextPrimary,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text =
                        activity.description.ifBlank {
                            "Sin descripción"
                        },
                    color = EverpathTextSecondary,
                    style =
                        MaterialTheme
                            .typography
                            .bodySmall,
                    modifier =
                        Modifier.padding(
                            top = 4.dp
                        ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier =
                        Modifier.padding(
                            top = 10.dp
                        ),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Box(
                        modifier =
                            Modifier
                                .size(8.dp)
                                .background(
                                    color = statusColor,
                                    shape = CircleShape
                                )
                    )

                    Spacer(
                        modifier = Modifier.width(6.dp)
                    )

                    Text(
                        text =
                            "Estado: ${activity.status.toSpanishName()}",
                        color = statusColor,
                        style =
                            MaterialTheme
                                .typography
                                .labelMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }

            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Box(
                modifier =
                    Modifier
                        .size(58.dp)
                        .background(
                            color =
                                statusColor.copy(
                                    alpha = 0.10f
                                ),
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = activity.status.toActivityIconRes()
                        ),
                    contentDescription =
                        activity.status.toSpanishName(),
                    modifier =
                        Modifier.size(36.dp),
                    contentScale =
                        ContentScale.Fit
                )

            }

        }

    }

}

private fun ActivityStatus.toSpanishName(): String {

    return when (this) {

        ActivityStatus.PENDING ->
            "Pendiente"

        ActivityStatus.IN_PROGRESS ->
            "En progreso"

        ActivityStatus.COMPLETED ->
            "Completada"

    }

}

@DrawableRes
private fun ActivityStatus.toActivityIconRes(): Int {

    return when (this) {

        ActivityStatus.PENDING ->
            R.drawable.ic_activity_pending

        ActivityStatus.IN_PROGRESS ->
            R.drawable.ic_activity_in_progress

        ActivityStatus.COMPLETED ->
            R.drawable.ic_activity_completed

    }

}

private fun ActivityStatus.toActivityColor(): Color {

    return when (this) {

        ActivityStatus.PENDING ->
            Color(0xFFD76A5E)

        ActivityStatus.IN_PROGRESS ->
            Color(0xFFC28A22)

        ActivityStatus.COMPLETED ->
            EverpathStatusCompleted

    }

}

private fun ActivityStatus.toActivityContainerColor(): Color {

    return when (this) {

        ActivityStatus.PENDING ->
            Color(0xFFF4E3E1)

        ActivityStatus.IN_PROGRESS ->
            Color(0xFFF5E8CC)

        ActivityStatus.COMPLETED ->
            EverpathStatusCompletedContainer

    }

}