package com.everpath.presentation.everpath.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.GoalNode

@Composable
fun GoalNodeCard(
    goalNode: GoalNode,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.size(
            width = 180.dp,
            height = 100.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {

            Text(
                text = goalNode.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = goalNode.lifeArea.name,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = goalNode.status.name,
                style = MaterialTheme.typography.bodySmall
            )

        }

    }
}