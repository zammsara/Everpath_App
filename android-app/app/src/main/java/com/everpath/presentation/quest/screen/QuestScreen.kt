package com.everpath.presentation.quest.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.EverpathApplication
import com.everpath.presentation.quest.viewmodel.QuestViewModel
import com.everpath.presentation.quest.viewmodel.QuestViewModelFactory

/**
 * Pantalla principal de misiones.
 *
 * Actualmente conecta la UI con
 * QuestViewModel para preparar
 * las siguientes fases.
 */
@Composable
fun QuestScreen() {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {
        QuestViewModelFactory(
            getGoalNodesUseCase =
                application
                    .appContainer
                    .getGoalNodesUseCase
        )
    }

    val viewModel: QuestViewModel =
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

    Text(
        text =
            "Quest listo para A3.2"
    )
}