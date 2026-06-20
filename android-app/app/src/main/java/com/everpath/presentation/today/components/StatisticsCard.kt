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

/**
 * Card encargada de mostrar
 * estadísticas generales del usuario.
 */
@Composable
fun StatisticsCard(
    goalCount: Int,
    completedGoalCount: Int,
    activityCount: Int,
    completedActivityCount: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth()

    ) {
        Column(
            modifier = Modifier.padding(16.dp)

        ) {
            Text(
                text = "Resumen General",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )

            Text("Metas Totales: $goalCount")

            Text("Metas Completados: $completedGoalCount")

            Text("Actividades Totales: $activityCount")

            Text("Actividades Completadas: $completedActivityCount")

        }

    }

}