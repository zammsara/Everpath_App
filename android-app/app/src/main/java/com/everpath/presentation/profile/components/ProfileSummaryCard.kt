package com.everpath.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Resumen textual del progreso
 * general del usuario.
 */
@Composable
fun ProfileSummaryCard(
    completedGoals: Int,
    completedActivities: Int
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
                text =
                    """
                    Has completado $completedGoals metas
                    y $completedActivities actividades.

                    Sigue avanzando.
                    """.trimIndent()
            )
        }
    }
}