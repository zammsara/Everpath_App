package com.everpath.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.usecase.achievement.GetAchievementsUseCase
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.FetchUserProgressUseCase
import com.everpath.domain.usecase.userprogress.GetLevelProgressUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import com.everpath.presentation.profile.state.ProfileUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar
 * las estadísticas globales del perfil.
 */
class ProfileViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val fetchUserProgressUseCase: FetchUserProgressUseCase,
    private val getUserLevelUseCase: GetUserLevelUseCase,
    private val getLevelProgressUseCase: GetLevelProgressUseCase,
    private val getAchievementsUseCase: GetAchievementsUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ProfileUiState()
        )

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()

    init {
        syncUserProgress()
        observeProfile()
    }

    private fun observeProfile() {

        viewModelScope.launch {

            getGoalNodesUseCase()
                .combine(
                    getUserProgressUseCase()
                ) { goals, progress ->

                    Pair(
                        goals,
                        progress
                    )

                }
                .combine(
                    getAchievementsUseCase()
                ) { (goals, progress), achievements ->

                    Triple(
                        goals,
                        progress,
                        achievements
                    )
                }
                .collect { (goals, progress, achievements) ->

                    val goalCount =
                        goals.size

                    val completedGoalCount =
                        goals.count {
                            it.status ==
                                    GoalStatus.COMPLETED
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
                        if (activityCount == 0)
                            0f
                        else
                            completedActivityCount.toFloat() /
                                    activityCount.toFloat()

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

                            goalCount =
                                goalCount,

                            completedGoalCount =
                                completedGoalCount,

                            activityCount =
                                activityCount,

                            completedActivityCount =
                                completedActivityCount,

                            globalProgress =
                                globalProgress,

                            xp =
                                xp,

                            level =
                                level,

                            levelProgress =
                                levelProgress,

                            achievements =
                                achievements,

                            isLoading =
                                false
                        )
                    }
                }
        }
    }



    /**
     * Solicita la sincronización del
     * progreso del usuario desde el
     * backend hacia Room.
     */
    private fun syncUserProgress() {
        viewModelScope.launch {
            fetchUserProgressUseCase()
        }
    }
}