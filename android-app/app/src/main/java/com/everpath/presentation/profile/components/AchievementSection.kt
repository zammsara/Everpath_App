package com.everpath.presentation.profile.components

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
 * Componente encargado de mostrar
 * el historial completo de logros
 * del usuario dentro del perfil.
 */
@Composable
fun AchievementSection(
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

            achievements.forEach { achievement ->
                val icon =
                    if (achievement.unlocked) {
                        "🏆"
                    } else {
                        "🔒"
                    }

                Text(
                    text =
                        "$icon ${achievement.title}",
                    modifier =
                        Modifier.padding(
                            top = 8.dp
                        )
                )
            }
        }
    }
}