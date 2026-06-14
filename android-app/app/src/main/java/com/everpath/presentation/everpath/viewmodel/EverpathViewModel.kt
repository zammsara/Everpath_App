package com.everpath.presentation.everpath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.goal.SaveGoalNodeUseCase
import com.everpath.domain.usecase.goalposition.SaveGoalPositionUseCase
import com.everpath.presentation.everpath.state.EverpathUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class EverpathViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val saveGoalNodeUseCase: SaveGoalNodeUseCase,
    private val saveGoalPositionUseCase: SaveGoalPositionUseCase
) : ViewModel() {


    private val _uiState =
        MutableStateFlow(
            EverpathUiState(
                isLoading = true
            )
        )

    val uiState: StateFlow<EverpathUiState> =
        _uiState.asStateFlow()

    init {
        observeGoals()
    }

    private fun observeGoals() {

        viewModelScope.launch {

            getGoalNodesUseCase()
                .collect { goals ->

                    _uiState.update { currentState ->

                        currentState.copy(
                            goalNodes = goals,
                            isLoading = false
                        )

                    }

                }

        }

    }

    fun createGoal(
        title: String,
        description: String
    ) {

        val goalId =
            UUID.randomUUID().toString()

        val goal = GoalNode(
            id = goalId,
            title = title,
            description = description,
            lifeArea = LifeAreaType.HEALTH,
            status = GoalStatus.ACTIVE,
            activities = emptyList()
        )

        val position =
            GoalPositionEntity(
                goalId = goalId,

                x = (
                        100f +
                                (_uiState.value.goalNodes.size * 220f)
                        ),

                y = 100f
            )

        viewModelScope.launch {

            saveGoalNodeUseCase(goal)

            saveGoalPositionUseCase(position)

        }
    }

    fun selectGoal(
        goalId: String
    ) {

        _uiState.update { currentState ->

            currentState.copy(
                selectedGoalId = goalId
            )

        }

    }

}