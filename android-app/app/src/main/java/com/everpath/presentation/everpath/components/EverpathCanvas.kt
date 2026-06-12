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

@Composable
fun EverpathCanvas(
    goalNodes: List<GoalNode>,
    positions: List<GoalNodePosition>,
    connections: List<GoalConnection>,
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
    ) {

        Canvas(
            modifier = Modifier.size(
                width = 1200.dp,
                height = 1200.dp
            )
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
                        color = connectionColor,
                        start = androidx.compose.ui.geometry.Offset(
                            x = sourceX + cardWidthPx / 2f,
                            y = sourceY + cardHeightPx / 2f
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            x = targetX + cardWidthPx / 2f,
                            y = targetY + cardHeightPx / 2f
                        ),
                        strokeWidth = 12f
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