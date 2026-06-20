package com.everpath.presentation.today.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Estado vacío cuando no existen
 * metas activas registradas.
 */
@Composable
fun EmptyGoalsCard() {
    Card(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Text(
            text =
                "Todavía no tienes metas activas.",

            modifier =
                Modifier.padding(16.dp)
        )

    }

}