package com.everpath.presentation.activity.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.everpath.domain.model.Activity

@Composable
fun ActivityCard(
    activity: Activity,
    onClick: () -> Unit
) {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        onClick = onClick,
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
    ) {

        Column(
            modifier =
                Modifier.padding(
                    12.dp
                )
        ) {

            Text(
                text = activity.title,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Text(
                text = activity.description,
                style =
                    MaterialTheme
                        .typography
                        .bodyMedium
            )

            Text(
                text =
                    "Estado: ${activity.status}",
                style =
                    MaterialTheme
                        .typography
                        .bodySmall
            )

        }

    }

}