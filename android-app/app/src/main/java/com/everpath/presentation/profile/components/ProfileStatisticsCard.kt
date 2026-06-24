package com.everpath.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Card encargada de mostrar
 * estadísticas generales del perfil.
 */
@Composable
fun ProfileStatisticsCard(
    goalCount: Int,
    completedGoalCount: Int,
    activityCount: Int,
    completedActivityCount: Int
) {

    Card(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Row(
            modifier =
                Modifier.padding(16.dp),
            horizontalArrangement =
                Arrangement.SpaceEvenly
        ) {

            Text("Metas\n$goalCount")

            Text("Completadas\n$completedGoalCount")

            Text("Actividades\n$activityCount")

            Text("Completadas\n$completedActivityCount")

        }

    }

}