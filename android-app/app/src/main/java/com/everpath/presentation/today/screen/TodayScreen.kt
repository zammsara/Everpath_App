package com.everpath.presentation.today.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.everpath.EverpathApplication
import com.everpath.presentation.today.components.DashboardHeader
import com.everpath.presentation.today.components.EmptyGoalsCard
import com.everpath.presentation.today.components.GoalSummaryCard
import com.everpath.presentation.today.components.ProgressCard
import com.everpath.presentation.today.components.StatisticsCard
import com.everpath.presentation.today.viewmodel.TodayViewModel
import com.everpath.presentation.today.viewmodel.TodayViewModelFactory
import com.everpath.presentation.today.components.LevelProgressCard

/**
 * Dashboard principal de Everpath.
 *
 * Muestra un resumen global del
 * progreso actual del usuario.
 */
@Composable
fun TodayScreen() {

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {
        TodayViewModelFactory(
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

            getLevelProgressUseCase =
                application
                    .appContainer
                    .getLevelProgressUseCase
        )
    }

    val viewModel: TodayViewModel =
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
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(16.dp),
        verticalArrangement =
            Arrangement.spacedBy(16.dp)

    ) {
        DashboardHeader(
            xp = uiState.value.xp,
            level = uiState.value.level)

        uiState.value.levelProgress?.let {
            LevelProgressCard(
                levelProgress = it
            )
        }

        ProgressCard(
            progress = uiState.value.globalProgress
        )
        StatisticsCard(
            goalCount = uiState.value.goalCount,
            completedGoalCount = uiState.value.completedGoalCount,
            activityCount = uiState.value.activityCount,
            completedActivityCount = uiState.value.completedActivityCount
        )

        if (
            uiState.value.activeGoals.isEmpty()
        ) {
            EmptyGoalsCard()
        } else {
            GoalSummaryCard(
                goals =
                    uiState.value.activeGoals
            )
        }
    }
}