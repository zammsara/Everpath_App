package com.everpath.presentation.today.components

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
            Modifier.fillMaxWidth()
    ) {

        Column(
            modifier =
                Modifier.padding(16.dp)
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
                    Modifier.padding(top = 8.dp)
            )

            Text(
                text =
                    "${levelProgress.currentLevelXp} / " +
                            "${levelProgress.requiredXpForNextLevel} XP",
                modifier =
                    Modifier.padding(top = 4.dp)
            )

            LinearProgressIndicator(
                progress = {
                    levelProgress.progress
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
            )

            Text(
                text =
                    "${(levelProgress.progress * 100).toInt()}%",
                modifier =
                    Modifier.padding(top = 8.dp)
            )
        }
    }
}