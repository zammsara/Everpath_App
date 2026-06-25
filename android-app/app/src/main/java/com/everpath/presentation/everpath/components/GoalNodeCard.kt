package com.everpath.presentation.everpath.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode

/**
 * Componente visual que representa una meta dentro del mapa.
 *
 * Mantiene la lógica original de selección, click y arrastre,
 * aplicando únicamente un estilo visual tipo burbuja.
 */
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

    val shape =
        RoundedCornerShape(34.dp)

    val bubbleColor =
        goalNode.lifeArea.toBubbleColor()

    val borderColor =
        if (isSelected) {
            Color(0xFF7A5CFF)
        } else {
            Color.White.copy(
                alpha = 0.82f
            )
        }

    Box(
        modifier =
            modifier
                .size(
                    width = 180.dp,
                    height = 100.dp
                )
                .graphicsLayer {
                    scaleX =
                        if (isSelected) {
                            1.05f
                        } else {
                            1f
                        }

                    scaleY =
                        if (isSelected) {
                            1.05f
                        } else {
                            1f
                        }
                }
                .shadow(
                    elevation =
                        if (isSelected) {
                            14.dp
                        } else {
                            8.dp
                        },
                    shape = shape,
                    clip = false
                )
                .clip(shape)
                .background(
                    brush =
                        Brush.linearGradient(
                            colors =
                                listOf(
                                    Color.White.copy(
                                        alpha = 0.98f
                                    ),
                                    bubbleColor.copy(
                                        alpha = 0.92f
                                    ),
                                    bubbleColor.copy(
                                        alpha = 0.78f
                                    )
                                )
                        )
                )
                .border(
                    width =
                        if (isSelected) {
                            2.dp
                        } else {
                            1.dp
                        },
                    color = borderColor,
                    shape = shape
                )
                .pointerInput(goalNode.id) {

                    detectDragGestures(
                        onDragStart = {
                            onDragStart()
                        },
                        onDragEnd = {
                            onDragEnd()
                        },
                        onDragCancel = {
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
                )
    ) {

        BubbleGloss(
            modifier =
                Modifier.fillMaxSize()
        )

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 8.dp
                    ),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                BubbleIcon(
                    iconRes =
                        goalNode.lifeArea.toIconRes(),
                    backgroundColor =
                        Color.White.copy(
                            alpha = 0.48f
                        ),
                    size = 31,
                    iconSize = 21
                )

                BubbleIcon(
                    iconRes =
                        goalNode.status.toIconRes(),
                    backgroundColor =
                        Color.White.copy(
                            alpha = 0.38f
                        ),
                    size = 28,
                    iconSize = 18
                )

            }

            Spacer(
                modifier =
                    Modifier.height(2.dp)
            )

            Text(
                text = goalNode.title,
                color = Color(0xFF17122F),
                style =
                    MaterialTheme
                        .typography
                        .titleSmall,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier =
                    Modifier.fillMaxWidth()
            )

            Spacer(
                modifier =
                    Modifier.height(2.dp)
            )

            Text(
                text =
                    goalNode.description.ifBlank {
                        "Sin descripción"
                    },
                color = Color(0xFF6E6591),
                style =
                    MaterialTheme
                        .typography
                        .labelSmall,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier =
                    Modifier.fillMaxWidth()
            )

        }

    }

}

@Composable
private fun BubbleGloss(
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = modifier
    ) {

        drawOval(
            color =
                Color.White.copy(
                    alpha = 0.45f
                ),
            topLeft =
                Offset(
                    x = 14.dp.toPx(),
                    y = 7.dp.toPx()
                ),
            size =
                Size(
                    width = 78.dp.toPx(),
                    height = 24.dp.toPx()
                )
        )

        drawCircle(
            color =
                Color.White.copy(
                    alpha = 0.42f
                ),
            radius = 8.dp.toPx(),
            center =
                Offset(
                    x = size.width - 25.dp.toPx(),
                    y = 18.dp.toPx()
                )
        )

        drawCircle(
            color =
                Color.White.copy(
                    alpha = 0.22f
                ),
            radius = 18.dp.toPx(),
            center =
                Offset(
                    x = size.width - 30.dp.toPx(),
                    y = size.height - 20.dp.toPx()
                )
        )

        drawCircle(
            color =
                Color.White.copy(
                    alpha = 0.18f
                ),
            radius = 13.dp.toPx(),
            center =
                Offset(
                    x = 28.dp.toPx(),
                    y = size.height - 18.dp.toPx()
                )
        )

    }

}

@Composable
private fun BubbleIcon(
    @DrawableRes iconRes: Int,
    backgroundColor: Color,
    size: Int,
    iconSize: Int
) {

    Box(
        modifier =
            Modifier
                .size(size.dp)
                .background(
                    color = backgroundColor,
                    shape = CircleShape
                ),
        contentAlignment =
            Alignment.Center
    ) {

        Image(
            painter =
                painterResource(
                    id = iconRes
                ),
            contentDescription = null,
            modifier =
                Modifier.size(iconSize.dp),
            contentScale =
                ContentScale.Fit
        )

    }

}

private fun LifeAreaType.toBubbleColor(): Color {

    return when (this) {

        LifeAreaType.HEALTH ->
            Color(0xFFDDF0FF)

        LifeAreaType.STUDIES ->
            Color(0xFFE4F5DC)

        LifeAreaType.CAREER ->
            Color(0xFFE2EEF4)

        LifeAreaType.FINANCE ->
            Color(0xFFFFF1CD)

        LifeAreaType.RELATIONSHIPS ->
            Color(0xFFF7DDE8)

        LifeAreaType.CREATIVITY ->
            Color(0xFFF3DDD2)

        LifeAreaType.TRAVEL ->
            Color(0xFFE6DBFA)

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

@DrawableRes
private fun GoalStatus.toIconRes(): Int {

    return when (this) {

        GoalStatus.LOCKED ->
            R.drawable.ic_status_locked

        GoalStatus.ACTIVE ->
            R.drawable.ic_status_active

        GoalStatus.COMPLETED ->
            R.drawable.ic_activity_completed

        GoalStatus.ARCHIVED ->
            R.drawable.ic_status_archived

    }

}