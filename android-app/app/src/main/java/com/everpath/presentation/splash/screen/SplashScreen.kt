package com.everpath.presentation.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.everpath.R
import com.everpath.presentation.splash.viewmodel.SplashViewModel
import com.everpath.ui.theme.EverpathBackground
import com.everpath.ui.theme.EverpathPrimary
import com.everpath.ui.theme.EverpathSurfaceSoft
import com.everpath.ui.theme.EverpathTextSecondary

/**
 * Pantalla inicial encargada
 * de restaurar la sesión del usuario.
 *
 * Su responsabilidad consiste en:
 *
 * - observar SplashViewModel;
 * - mostrar el branding;
 * - informar cuándo finaliza
 *   la restauración de sesión.
 */
@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onSessionRestored: () -> Unit,
    onSessionNotFound: () -> Unit

) {

    val uiState by
    viewModel
        .uiState
        .collectAsState()

    LaunchedEffect(
        uiState.isLoading
    ) {

        if (!uiState.isLoading) {

            if (uiState.hasSession) {

                onSessionRestored()

            } else {

                onSessionNotFound()

            }

        }

    }

    SplashScreenContent()

}

@Composable
private fun SplashScreenContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(
                    colors = listOf(
                        EverpathBackground,
                        EverpathSurfaceSoft,
                        EverpathBackground
                    )
                )
            ),

        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Image(
            painter =
                painterResource(
                    R.drawable.everpath_logo
                ),
            contentDescription = null,
            modifier = Modifier.size(190.dp)

        )

        Text(
            text = "Everpath",
            style = MaterialTheme
                    .typography
                    .headlineLarge,
            fontWeight = FontWeight.Bold,
            color = EverpathPrimary

        )

        Text(

            text = "Tu camino hacia tus metas",

            color =  EverpathTextSecondary

        )

        androidx.compose.foundation.layout.Spacer(

            modifier = Modifier.size(36.dp)

        )

        CircularProgressIndicator(

            color =  EverpathPrimary
        )

    }

}