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
import com.everpath.domain.model.Achievement

/**
 * Card encargada de mostrar
 * todos los achievements del usuario.
 */
@Composable
fun AchievementsCard(
    achievements: List<Achievement>
) {

    Card(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {

            Text(
                text = "Logros",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            achievements.forEach {
                AchievementItem(
                    achievement = it
                )
            }
        }
    }
}