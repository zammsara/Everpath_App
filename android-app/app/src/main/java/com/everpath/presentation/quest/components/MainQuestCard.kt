package com.everpath.presentation.quest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.GoalNode

/**
 * Card principal de Quest.
 *
 * Representa la misión principal
 * actual del usuario.
 */
@Composable
fun MainQuestCard(
    quest: GoalNode?
) {

    if (quest == null) {
        return
    }

    Card(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {

            Text(
                text = "Mi Misión Actual",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            Text(
                text = quest.title,
                modifier =
                    Modifier.padding(top = 12.dp)
            )

            LinearProgressIndicator(
                progress = {
                    quest.progress
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
            )

            Text(
                text =
                    "${(quest.progress * 100).toInt()}%",
                modifier =
                    Modifier.padding(top = 8.dp)
            )
        }
    }
}