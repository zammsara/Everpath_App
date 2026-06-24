package com.everpath.presentation.today.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Encabezado principal del Dashboard.
 *
 * Da contexto al usuario sobre
 * el estado general de su progreso.
 */
@Composable
fun DashboardHeader(
    xp: Int,
    level: Int
) {
    Column {
        Text(
            text = "Hola!",
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Text(
            text = "Nivel $level",
            modifier =
                Modifier.padding(top = 4.dp),
            style =
                MaterialTheme
                    .typography
                    .titleMedium
        )

        Text(

            text = "$xp XP",

            modifier =
                Modifier.padding(top = 4.dp),

            style =
                MaterialTheme
                    .typography
                    .titleMedium

        )

        Text(
            text =
                "Este es tu panorama general.",
            modifier =
                Modifier.padding(top = 4.dp),
            style =
                MaterialTheme
                    .typography
                    .bodyMedium
        )
    }
}