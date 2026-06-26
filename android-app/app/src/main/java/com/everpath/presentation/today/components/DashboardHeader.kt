package com.everpath.presentation.today.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextPrimary
import com.everpath.ui.theme.EverpathTextSecondary

@Composable
fun DashboardHeader(
    xp: Int,
    level: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Everpath",
                color = EverpathTextPrimary,
                style = MaterialTheme.typography.displaySmall,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "Tu mapa de progreso personal",
                color = EverpathTextSecondary,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Text(
                text = "Nivel $level",
                color = EverpathPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = "$xp XP",
                color = EverpathTextPrimary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(
            modifier = Modifier.size(12.dp)
        )

        Box(
            modifier =
                Modifier
                    .size(92.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .background(
                        color = EverpathSurfaceSoft,
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.85f),
                        shape = CircleShape
                    ),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter =
                    painterResource(
                        id = R.drawable.everpath_logo
                    ),
                contentDescription = "Logo Everpath",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Fit
            )

        }

    }

}