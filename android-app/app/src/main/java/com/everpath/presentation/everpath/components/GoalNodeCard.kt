package com.everpath.presentation.everpath.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

/**
 * Componente visual que representa una Goal dentro del mapa.
 */
@Composable
fun GoalNodeCard(
    goalNode: GoalNode,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.size(
            width = 180.dp,
            height = 100.dp
        ).border(
            width = if (isSelected) 3.dp else 1.dp,
            color = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.outline,
            shape = CardDefaults.shape
        )
            .clickable(
                onClick = onClick
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation =
                if (isSelected) {
                    12.dp
                } else {
                    4.dp
                }
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