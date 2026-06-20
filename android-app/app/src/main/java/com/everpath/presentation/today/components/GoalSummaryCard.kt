package com.everpath.presentation.today.components

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

/**
 * Card encargada de mostrar
 * las metas activas del usuario.
 */
@Composable
fun GoalSummaryCard(
    goals: List<GoalNode>
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Metas Activas",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )
            goals.forEach { goal ->
                Text( text = "• ${goal.title}")
            }
        }
    }
}