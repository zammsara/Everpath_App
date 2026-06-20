package com.everpath.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import com.everpath.presentation.profile.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar
 * las estadísticas globales del perfil.
 */
class ProfileViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ProfileUiState()
        )

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()

    init {
        loadProfileData()
        loadUserProgress()
    }

    private fun loadProfileData() {
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

                                it.status.name ==
                                        "COMPLETED"
                            }
                        }

                    val globalProgress =
                        if (
                            activityCount == 0
                        ) {
                            0f
                        } else {
                            completedActivityCount
                                .toFloat() /
                                    activityCount
                                        .toFloat()
                        }

                    _uiState.update {
                        it.copy(
                            goalCount = goalCount,
                            completedGoalCount = completedGoalCount,
                            activityCount = activityCount,
                            completedActivityCount = completedActivityCount,
                            globalProgress = globalProgress,
                            isLoading = false

                        )
                    }
                }
        }
    }

    private fun loadUserProgress() {

        viewModelScope.launch {

            getUserProgressUseCase()
                .collect { userProgress ->
                    _uiState.update {
                        it.copy(
                            xp = userProgress?.xp ?: 0
                        )
                    }
                }
        }
    }
}