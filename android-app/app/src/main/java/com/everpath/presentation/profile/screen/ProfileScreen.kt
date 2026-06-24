package com.everpath.presentation.profile.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.everpath.presentation.components.LevelProgressCard
import com.everpath.presentation.profile.components.AchievementSection
import com.everpath.presentation.profile.components.ProfileHeader
import com.everpath.presentation.profile.components.ProfileProgressCard
import com.everpath.presentation.profile.components.ProfileStatisticsCard
import com.everpath.presentation.profile.components.ProfileSummaryCard
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

    val scrollState =
        rememberScrollState()

    val application =
        LocalContext.current.applicationContext
                as EverpathApplication

    val factory = remember {
        ProfileViewModelFactory(
            getGoalNodesUseCase =
                application.appContainer.getGoalNodesUseCase,

            getUserProgressUseCase =
                application.appContainer.getUserProgressUseCase,

            getUserLevelUseCase =
                application.appContainer.getUserLevelUseCase,

            getAchievementsUseCase =
                application.appContainer.getAchievementsUseCase,

            getLevelProgressUseCase =
                application.appContainer.getLevelProgressUseCase
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

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    scrollState
                )
                .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ProfileHeader(
            xp = uiState.value.xp,
            level = uiState.value.level)

        uiState.value.levelProgress?.let { levelProgress ->

            LevelProgressCard(
                levelProgress = levelProgress
            )

        }

        ProfileStatisticsCard(
            goalCount = uiState.value.goalCount,
            completedGoalCount = uiState.value.completedGoalCount,
            activityCount = uiState.value.activityCount,
            completedActivityCount = uiState.value.completedActivityCount
        )

        ProfileProgressCard(
            progress = uiState.value.globalProgress
        )

        ProfileSummaryCard(
            completedGoals = uiState.value.completedGoalCount,
            completedActivities = uiState.value.completedActivityCount
        )

        AchievementSection(
            achievements =
                uiState.value
                    .achievements
        )

    }

}