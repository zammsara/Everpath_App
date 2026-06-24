package com.everpath.presentation.everpath.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode
import com.everpath.ui.theme.EverpathAreaCareer
import com.everpath.ui.theme.EverpathAreaCareerContainer
import com.everpath.ui.theme.EverpathAreaCreativity
import com.everpath.ui.theme.EverpathAreaCreativityContainer
import com.everpath.ui.theme.EverpathAreaFinance
import com.everpath.ui.theme.EverpathAreaFinanceContainer
import com.everpath.ui.theme.EverpathAreaHealth
import com.everpath.ui.theme.EverpathAreaHealthContainer
import com.everpath.ui.theme.EverpathAreaRelationships
import com.everpath.ui.theme.EverpathAreaRelationshipsContainer
import com.everpath.ui.theme.EverpathAreaStudies
import com.everpath.ui.theme.EverpathAreaStudiesContainer
import com.everpath.ui.theme.EverpathAreaTravel
import com.everpath.ui.theme.EverpathAreaTravelContainer
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathStatusActive
import com.everpath.ui.theme.EverpathStatusArchived
import com.everpath.ui.theme.EverpathStatusCompleted
import com.everpath.ui.theme.EverpathStatusLocked
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary

@Composable
fun GoalNodeCard(
    goalNode: GoalNode,
    isSelected: Boolean,
    onClick: () -> Unit,

    onDragStart: () -> Unit = {},
    onDrag: (Float, Float) -> Unit = { _, _ -> },
    onDragEnd: () -> Unit = {},

    modifier: Modifier = Modifier
) {

    val areaColor =
        goalNode.lifeArea.toColor()

    val containerColor =
        goalNode.lifeArea.toContainerColor()

    Card(
        modifier =
            modifier
                .size(
                    width = 180.dp,
                    height = 100.dp
                )
                .border(
                    width =
                        if (isSelected) {
                            3.dp
                        } else {
                            1.dp
                        },
                    color =
                        if (isSelected) {
                            areaColor
                        } else {
                            EverpathBorder
                        },
                    shape = RoundedCornerShape(22.dp)
                )
                .pointerInput(goalNode.id) {
                    detectDragGestures(
                        onDragStart = {
                            onDragStart()
                        },
                        onDragEnd = {
                            onDragEnd()
                        }
                    ) { change, dragAmount ->

                        change.consume()

                        onDrag(
                            dragAmount.x,
                            dragAmount.y
                        )
                    }
                }
                .clickable(
                    onClick = onClick
                ),
        colors =
            CardDefaults.cardColors(
                containerColor = containerColor
            ),
        shape = RoundedCornerShape(22.dp),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation =
                    if (isSelected) {
                        12.dp
                    } else {
                        5.dp
                    }
            )
    ) {

        Column(
            modifier =
                Modifier.padding(12.dp)
        ) {

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier =
                        Modifier
                            .size(34.dp)
                            .background(
                                color =
                                    areaColor.copy(
                                        alpha = 0.18f
                                    ),
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id = goalNode.lifeArea.toIconRes()
                            ),
                        contentDescription =
                            goalNode.lifeArea.toSpanishName(),
                        modifier =
                            Modifier.size(22.dp),
                        contentScale =
                            ContentScale.Fit
                    )

                }

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {

                    Text(
                        text = goalNode.title,
                        color = EverpathTextPrimary,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = goalNode.lifeArea.toSpanishName(),
                        color = EverpathTextSecondary,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }

                Box(
                    modifier =
                        Modifier
                            .size(28.dp)
                            .background(
                                color = EverpathSurfaceSoft,
                                shape = CircleShape
                            )
                            .border(
                                width = 1.dp,
                                color = EverpathBorder,
                                shape = CircleShape
                            ),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Image(
                        painter =
                            painterResource(
                                id = goalNode.status.toIconRes()
                            ),
                        contentDescription =
                            goalNode.status.toSpanishName(),
                        modifier =
                            Modifier.size(18.dp),
                        contentScale =
                            ContentScale.Fit
                    )

                }

            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = goalNode.status.toSpanishName(),
                color = goalNode.status.toColor(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = goalNode.description,
                color =
                    EverpathTextSecondary.copy(
                        alpha = 0.85f
                    ),
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }

    }

}

private fun LifeAreaType.toSpanishName(): String {

    return when (this) {

        LifeAreaType.HEALTH ->
            "Salud"

        LifeAreaType.STUDIES ->
            "Estudios"

        LifeAreaType.CAREER ->
            "Carrera"

        LifeAreaType.FINANCE ->
            "Finanzas"

        LifeAreaType.RELATIONSHIPS ->
            "Relaciones"

        LifeAreaType.CREATIVITY ->
            "Creatividad"

        LifeAreaType.TRAVEL ->
            "Viajes"
    }
}

@DrawableRes
private fun LifeAreaType.toIconRes(): Int {

    return when (this) {

        LifeAreaType.HEALTH ->
            R.drawable.ic_area_health

        LifeAreaType.STUDIES ->
            R.drawable.ic_area_studies

        LifeAreaType.CAREER ->
            R.drawable.ic_area_career

        LifeAreaType.FINANCE ->
            R.drawable.ic_area_finance

        LifeAreaType.RELATIONSHIPS ->
            R.drawable.ic_area_relationships

        LifeAreaType.CREATIVITY ->
            R.drawable.ic_area_creativity

        LifeAreaType.TRAVEL ->
            R.drawable.ic_area_travel
    }
}

private fun LifeAreaType.toColor(): Color {

    return when (this) {

        LifeAreaType.HEALTH ->
            EverpathAreaHealth

        LifeAreaType.STUDIES ->
            EverpathAreaStudies

        LifeAreaType.CAREER ->
            EverpathAreaCareer

        LifeAreaType.FINANCE ->
            EverpathAreaFinance

        LifeAreaType.RELATIONSHIPS ->
            EverpathAreaRelationships

        LifeAreaType.CREATIVITY ->
            EverpathAreaCreativity

        LifeAreaType.TRAVEL ->
            EverpathAreaTravel
    }
}

private fun LifeAreaType.toContainerColor(): Color {

    return when (this) {

        LifeAreaType.HEALTH ->
            EverpathAreaHealthContainer

        LifeAreaType.STUDIES ->
            EverpathAreaStudiesContainer

        LifeAreaType.CAREER ->
            EverpathAreaCareerContainer

        LifeAreaType.FINANCE ->
            EverpathAreaFinanceContainer

        LifeAreaType.RELATIONSHIPS ->
            EverpathAreaRelationshipsContainer

        LifeAreaType.CREATIVITY ->
            EverpathAreaCreativityContainer

        LifeAreaType.TRAVEL ->
            EverpathAreaTravelContainer
    }
}

private fun GoalStatus.toSpanishName(): String {

    return when (this) {

        GoalStatus.LOCKED ->
            "Bloqueada"

        GoalStatus.ACTIVE ->
            "Activa"

        GoalStatus.COMPLETED ->
            "Completada"

        GoalStatus.ARCHIVED ->
            "Archivada"
    }
}

@DrawableRes
private fun GoalStatus.toIconRes(): Int {

    return when (this) {

        GoalStatus.LOCKED ->
            R.drawable.ic_status_locked

        GoalStatus.ACTIVE ->
            R.drawable.ic_status_active

        GoalStatus.COMPLETED ->
            R.drawable.ic_status_completed

        GoalStatus.ARCHIVED ->
            R.drawable.ic_status_archived
    }
}

private fun GoalStatus.toColor(): Color {

    return when (this) {

        GoalStatus.LOCKED ->
            EverpathStatusLocked

        GoalStatus.ACTIVE ->
            EverpathStatusActive

        GoalStatus.COMPLETED ->
            EverpathStatusCompleted

        GoalStatus.ARCHIVED ->
            EverpathStatusArchived
    }
}