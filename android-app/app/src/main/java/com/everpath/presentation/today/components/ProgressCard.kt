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

/**
 * Card encargada de mostrar
 * el progreso global del usuario.
 */
@Composable
fun ProgressCard(
    progress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Progreso General",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            LinearProgressIndicator(
                progress = { progress },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)

            )

            Text(
                text =
                    "${(progress * 100).toInt()}%",
                modifier =
                    Modifier.padding(
                        top = 8.dp
                    )
            )
        }
    }
}