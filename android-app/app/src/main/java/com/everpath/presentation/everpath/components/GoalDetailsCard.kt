package com.everpath.presentation.everpath.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.GoalNode

@Composable
fun GoalDetailsCard(
    goalNode: GoalNode,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "Goal seleccionado",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            Text(
                text = goalNode.title,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                modifier =
                    Modifier.padding(top = 12.dp)
            )

            Text(
                text = goalNode.description,
                modifier =
                    Modifier.padding(top = 8.dp)
            )

            Text(
                text =
                    "Área: ${goalNode.lifeArea.name}",
                modifier =
                    Modifier.padding(top = 12.dp)
            )

            Text(
                text =
                    "Estado: ${goalNode.status.name}",
                modifier =
                    Modifier.padding(top = 4.dp)
            )

        }

    }

}