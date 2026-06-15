package com.everpath.presentation.goaldetail.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun GoalDetailScreen(
    goalId: String
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = """
                Pantalla de Detalle de Meta
                
                Aquí el usuario podrá:
                
                • Visualizar la información completa de una meta.
                • Consultar el porcentaje de progreso.
                • Leer la descripción y propósito de la meta.
                • Ver actividades y hábitos relacionados.
                • Gestionar conexiones con otras metas.
                • Editar o actualizar el estado de la meta.
                • Dar seguimiento a los avances alcanzados.
            """.trimIndent()
        )

    }

}