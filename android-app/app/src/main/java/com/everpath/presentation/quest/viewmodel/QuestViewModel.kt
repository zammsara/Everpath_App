package com.everpath.presentation.quest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.presentation.quest.state.QuestUiState
import com.everpath.domain.usecase.achievement.EvaluateAchievementsUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.combine

/**
 * ViewModel encargado de gestionar
 * la información de la pantalla Quest.
 */
class QuestViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val getUserLevelUseCase: GetUserLevelUseCase,
    private val evaluateAchievementsUseCase: EvaluateAchievementsUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            QuestUiState()
        )

    val uiState: StateFlow<QuestUiState> =
        _uiState.asStateFlow()

    init {
        loadQuestData()
    }

    private fun loadQuestData() {
        viewModelScope.launch {
            combine(
                getGoalNodesUseCase(),
                getUserProgressUseCase()

            ) { goals, progress ->
                Pair(
                    goals,
                    progress
                )
            }.collect { (goals, progress) ->

                val activeGoals =
                    goals.filter {
                        it.status ==
                                GoalStatus.ACTIVE
                    }

                val completedGoals =
                    goals.count {
                        it.status ==
                                GoalStatus.COMPLETED
                    }

                val xp =
                    progress?.xp ?: 0

                val level =
                    getUserLevelUseCase(
                        xp
                    )

                val achievements =
                    evaluateAchievementsUseCase(
                        goals = goals,
                        xp = xp,
                        level = level
                    )

                _uiState.update {
                    it.copy(
                        activeGoals = activeGoals,
                        completedGoals = completedGoals,
                        achievements = achievements,
                        isLoading = false
                    )
                }
            }
        }
    }
}