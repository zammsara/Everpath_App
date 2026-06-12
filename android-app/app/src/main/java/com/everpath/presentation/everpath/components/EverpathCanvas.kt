package com.everpath.presentation.everpath.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.GoalConnection
import com.everpath.domain.model.GoalNode
import com.everpath.presentation.everpath.model.GoalNodePosition

@Composable
fun EverpathCanvas(
    goalNodes: List<GoalNode>,
    positions: List<GoalNodePosition>,
    connections: List<GoalConnection>,
    modifier: Modifier = Modifier
) {

    val connectionColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            connections.forEach { connection ->

                val source = positions.find {
                    it.goalNodeId == connection.sourceGoalId
                }

                val target = positions.find {
                    it.goalNodeId == connection.targetGoalId
                }

                if (source != null && target != null) {

                    drawLine(
                        color = connectionColor,
                        start = androidx.compose.ui.geometry.Offset(
                            x = source.x,
                            y = source.y
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            x = target.x,
                            y = target.y
                        ),
                        strokeWidth = 6f
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
                    )
                )

            }

        }

    }
}