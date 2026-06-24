package com.everpath.presentation.today.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.GetLevelProgressUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import com.everpath.presentation.today.state.TodayUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsable del Dashboard.
 *
 * Gestiona las métricas generales
 * y el resumen de progreso del usuario.
 */
class TodayViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase:  GetUserProgressUseCase,
    private val getUserLevelUseCase: GetUserLevelUseCase,
    private val getLevelProgressUseCase: GetLevelProgressUseCase

) : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            TodayUiState()
        )

    val uiState: StateFlow<TodayUiState> =
        _uiState.asStateFlow()

    init {
        loadDashboard()
        loadUserProgress()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            getGoalNodesUseCase()
                .collect { goals ->

                    val goalCount = goals.size
                    val completedGoalCount =
                        goals.count {
                            it.status == GoalStatus.COMPLETED
                        }

                    val activityCount =
                        goals.sumOf {
                            it.activities.size
                        }

                    val completedActivityCount =
                        goals.sumOf { goal ->
                            goal.activities.count {
                                it.status.name == "COMPLETED"
                            }
                        }

                    val globalProgress =
                        if (
                            activityCount == 0
                        ) {
                            0f
                        } else {
                            completedActivityCount.toFloat() /
                                    activityCount.toFloat()

                        }

                    val activeGoals =
                        goals.filter {
                            it.status ==
                                    GoalStatus.ACTIVE
                        }

                    _uiState.update {
                        it.copy(
                            goalCount = goalCount,
                            completedGoalCount = completedGoalCount,
                            activityCount = activityCount,
                            completedActivityCount = completedActivityCount,
                            globalProgress = globalProgress,
                            activeGoals = activeGoals,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun loadUserProgress() {

        viewModelScope.launch {
            getUserProgressUseCase()
                .collect { progress ->
                    val xp =
                        progress?.xp ?: 0

                    val level =
                        getUserLevelUseCase(
                            xp
                        )

                    val levelProgress =
                        getLevelProgressUseCase(
                            xp
                        )

                    _uiState.update {
                        it.copy(
                            xp = xp,
                            level = level,
                            levelProgress = levelProgress
                        )
                    }
                }
        }
    }
}