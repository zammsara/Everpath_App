package com.everpath.presentation.quest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.Achievement

/**
 * Representa visualmente un achievement
 * individual dentro de la sección de logros.
 */
@Composable
fun AchievementItem(
    achievement: Achievement
) {

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
    ) {

        Text(
            text =
                if (achievement.unlocked)
                    "✅ ${achievement.title}"
                else
                    "🔒 ${achievement.title}",
            style =
                MaterialTheme
                    .typography
                    .titleMedium
        )

        Text(
            text =
                achievement.description,
            style =
                MaterialTheme
                    .typography
                    .bodyMedium
        )
    }
}