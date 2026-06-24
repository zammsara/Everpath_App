package com.everpath.presentation.quest.components

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
 * Card que muestra las metas
 * relacionadas con la misión actual.
 */
@Composable
fun QuestGoalsCard(
    goals: List<GoalNode>
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text =
                    "Metas Relacionadas",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            goals.forEach { goal ->

                Text(
                    text =
                        "• ${goal.title}",
                    modifier =
                        Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}