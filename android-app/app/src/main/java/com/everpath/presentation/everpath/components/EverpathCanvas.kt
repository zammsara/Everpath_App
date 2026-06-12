package com.everpath.presentation.everpath.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.GoalNode
import com.everpath.presentation.everpath.model.GoalNodePosition

@Composable
fun EverpathCanvas(
    goalNodes: List<GoalNode>,
    positions: List<GoalNodePosition>,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {

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