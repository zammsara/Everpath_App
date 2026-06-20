package com.everpath.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Encabezado principal del perfil.
 *
 * Proporciona contexto al usuario
 * sobre su progreso dentro de Everpath.
 */
@Composable
fun ProfileHeader() {

    Column {
        Text(
            text = "Usuario Everpath",
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Text(
            text = "Tu progreso personal",
            style =
                MaterialTheme
                    .typography
                    .bodyMedium
        )
    }
}