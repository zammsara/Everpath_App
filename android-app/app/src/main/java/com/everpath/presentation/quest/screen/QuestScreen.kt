package com.everpath.presentation.quest.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun QuestScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = """
                Pantalla de Misiones
                
                Aquí el usuario podrá:
                
                • Crear nuevas misiones.
                • Consultar misiones activas.
                • Ver el progreso de cada misión.
                • Relacionar hábitos con misiones.
                • Marcar objetivos como completados.
                • Visualizar detalles y conexiones.
            """.trimIndent()
        )

    }

}
