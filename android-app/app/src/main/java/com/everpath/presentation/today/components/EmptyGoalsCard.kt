package com.everpath.presentation.today.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.ui.theme.EverpathBorder
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary

@Composable
fun EmptyGoalsCard() {

    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(30.dp),
                    clip = false
                )
                .border(
                    width = 1.dp,
                    color =
                        EverpathPrimary.copy(
                            alpha = 0.20f
                        ),
                    shape = RoundedCornerShape(30.dp)
                ),
        shape =
            RoundedCornerShape(30.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = EverpathSurfaceSoft
            )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
            horizontalAlignment =
                Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.Center
        ) {

            Box(
                modifier =
                    Modifier
                        .size(84.dp)
                        .background(
                            color =
                                EverpathPrimary.copy(
                                    alpha = 0.12f
                                ),
                            shape = CircleShape
                        ),
                contentAlignment =
                    Alignment.Center
            ) {

                Image(
                    painter =
                        painterResource(
                            id = R.drawable.ic_goal_target
                        ),
                    contentDescription = "Sin metas",
                    modifier =
                        Modifier.size(52.dp),
                    contentScale =
                        ContentScale.Fit
                )

            }

            Spacer(
                modifier =
                    Modifier.size(16.dp)
            )

            Text(
                text = "Aún no hay metas activas",
                color = EverpathTextPrimary,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier =
                    Modifier.size(6.dp)
            )

            Text(
                text = "Crea una meta para comenzar a construir tu mapa personal.",
                color = EverpathTextSecondary,
                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,
                textAlign = TextAlign.Center
            )

        }

    }

}