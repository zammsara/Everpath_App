package com.everpath.presentation.quest.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.EverpathApplication
import com.everpath.presentation.quest.components.MainQuestCard
import com.everpath.presentation.quest.components.QuestGoalsCard
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
                    .getGoalNodesUseCase,
            getUserProgressUseCase =
                application
                    .appContainer
                    .getUserProgressUseCase,
            getUserLevelUseCase =
                application
                    .appContainer
                    .getUserLevelUseCase,
            evaluateAchievementsUseCase =
                application
                    .appContainer
                    .evaluateAchievementsUseCase
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

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        MainQuestCard(
            quest =
                uiState.value
                    .activeGoals
                    .firstOrNull()
        )

        QuestGoalsCard(
            goals =
                uiState.value
                    .activeGoals
        )
    }
}