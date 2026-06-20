package com.everpath.presentation.components

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
import com.everpath.domain.model.LevelProgress

/**
 * Card encargada de mostrar
 * el progreso actual del usuario
 * dentro de su nivel.
 */
@Composable
fun LevelProgressCard(
    levelProgress: LevelProgress
) {

    Card(
        modifier =
            Modifier.Companion.fillMaxWidth()
    ) {

        Column(
            modifier =
                Modifier.Companion.padding(16.dp)
        ) {

            Text(
                text = "Progreso de Nivel",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            Text(
                text =
                    "Nivel ${levelProgress.currentLevel}",
                modifier =
                    Modifier.Companion.padding(top = 8.dp)
            )

            Text(
                text =
                    "${levelProgress.currentLevelXp} / " +
                            "${levelProgress.requiredXpForNextLevel} XP",
                modifier =
                    Modifier.Companion.padding(top = 4.dp)
            )

            LinearProgressIndicator(
                progress = {
                    levelProgress.progress
                },
                modifier =
                    Modifier.Companion
                        .fillMaxWidth()
                        .padding(top = 12.dp)
            )

            Text(
                text =
                    "${(levelProgress.progress * 100).toInt()}%",
                modifier =
                    Modifier.Companion.padding(top = 8.dp)
            )
        }
    }
}