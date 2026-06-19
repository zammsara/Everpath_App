package com.everpath.presentation.everpath.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.GoalConnection
import com.everpath.domain.model.GoalNode
import com.everpath.presentation.everpath.model.GoalNodePosition
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.geometry.Offset
import com.everpath.presentation.everpath.util.ConnectionHitTest
import kotlin.math.abs

/**
 * Renderiza el mapa visual de Everpath y las conexiones entre Goals.
 */
@Composable
fun EverpathCanvas(
    goalNodes: List<GoalNode>,
    positions: List<GoalNodePosition>,
    connections: List<GoalConnection>,

    selectedGoalId: String?,
    selectedConnectionId: String?,

    onGoalClick: (String) -> Unit,
    onConnectionClick: (String) -> Unit,

    onBackgroundClick: () -> Unit,

    modifier: Modifier = Modifier
) {

    val connectionColor = MaterialTheme.colorScheme.primary

    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()

    Box(
        modifier = modifier
            .horizontalScroll(horizontalScrollState)
            .verticalScroll(verticalScrollState)
            .size(
                width = 1200.dp,
                height = 1200.dp
            )
            .clickable {
                onBackgroundClick()
            }
    ) {

        if (goalNodes.isEmpty()) {

            Text(
                text =
                    "Todavía no tienes metas.\n\nPresiona el botón + para crear tu primera meta.",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )

            return@Box

        }

        Canvas(
            modifier = Modifier
                .size(
                    width = 1200.dp,
                    height = 1200.dp
                )
                .pointerInput(connections) {
                    detectTapGestures { tapOffset ->
                        connections.forEach { connection ->

                            val source =
                                positions.find {
                                    it.goalNodeId ==
                                            connection.sourceGoalId
                                }

                            val target =
                                positions.find {
                                    it.goalNodeId ==
                                            connection.targetGoalId
                                }

                            if (
                                source != null &&
                                target != null
                            ) {

                                val cardWidthPx =
                                    180.dp.toPx()

                                val cardHeightPx =
                                    100.dp.toPx()

                                val start =
                                    Offset(
                                        x =
                                            source.x.dp.toPx() +
                                                    cardWidthPx / 2f,

                                        y =
                                            source.y.dp.toPx() +
                                                    cardHeightPx / 2f
                                    )

                                val end =
                                    Offset(
                                        x =
                                            target.x.dp.toPx() +
                                                    cardWidthPx / 2f,

                                        y =
                                            target.y.dp.toPx() +
                                                    cardHeightPx / 2f
                                    )

                                if (
                                    ConnectionHitTest
                                        .isPointNearLine(
                                            point = tapOffset,
                                            lineStart = start,
                                            lineEnd = end
                                        )
                                ) {

                                    onConnectionClick(
                                        connection.id
                                    )
                                    return@detectTapGestures
                                }
                            }
                        }
                    }
                }
        ) {

            connections.forEach { connection ->

                val source = positions.find {
                    it.goalNodeId == connection.sourceGoalId
                }

                val target = positions.find {
                    it.goalNodeId == connection.targetGoalId
                }

                if (source != null && target != null) {

                    val cardWidthPx = 180.dp.toPx()
                    val cardHeightPx = 100.dp.toPx()

                    val sourceX = source.x.dp.toPx()
                    val sourceY = source.y.dp.toPx()

                    val targetX = target.x.dp.toPx()
                    val targetY = target.y.dp.toPx()

                    drawLine(
                        color =
                            if (
                                connection.id ==
                                selectedConnectionId
                            ) {
                                androidx.compose.ui.graphics.Color.Red
                            } else {
                                connectionColor
                            },
                        start = androidx.compose.ui.geometry.Offset(
                            x = sourceX + cardWidthPx / 2f,
                            y = sourceY + cardHeightPx / 2f
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            x = targetX + cardWidthPx / 2f,
                            y = targetY + cardHeightPx / 2f
                        ),
                        strokeWidth =
                            if (
                                connection.id ==
                                selectedConnectionId
                            ) {
                                18f
                            } else {
                                12f
                            }
                    )

                }

            }

        }

        goalNodes.forEach { goalNode ->

            val position = positions.find {
                it.goalNodeId == goalNode.id
            }

            if (position != null) {

                GoalNodeCard(
                    goalNode = goalNode,
                    isSelected =
                        goalNode.id == selectedGoalId,
                    onClick = {
                        onGoalClick(goalNode.id)
                    },
                    modifier = Modifier.offset(
                        x = position.x.dp,
                        y = position.y.dp
                    ).size(
                        width = 180.dp,
                        height = 100.dp
                    ),
                )

            }

        }

    }
}