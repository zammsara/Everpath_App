package com.everpath.presentation.profile.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.EverpathApplication
import com.everpath.presentation.profile.viewmodel.ProfileViewModel
import com.everpath.presentation.profile.viewmodel.ProfileViewModelFactory

/**
 * Pantalla principal del perfil.
 *
 * Actualmente conecta el ViewModel
 * con la UI y prepara las estadísticas
 * para futuras fases.
 */
@Composable
fun ProfileScreen() {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {
        ProfileViewModelFactory(
            getGoalNodesUseCase =
                application
                    .appContainer
                    .getGoalNodesUseCase
        )
    }

    val viewModel: ProfileViewModel =
        viewModel(
            factory = factory
        )

    val uiState =
        viewModel
            .uiState
            .collectAsStateWithLifecycle()
    if (
        uiState.value.isLoading
    ) {
        CircularProgressIndicator()

        return
    }

    Text( text = "Perfil listo para A2.2")

}