package com.everpath.presentation.everpath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.data.local.entity.GoalPositionEntity
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.goal.SaveGoalNodeUseCase
import com.everpath.domain.usecase.goalposition.GetGoalPositionsUseCase
import com.everpath.domain.usecase.goalposition.SaveGoalPositionUseCase
import com.everpath.presentation.everpath.model.GoalNodePosition
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
    private val saveGoalPositionUseCase: SaveGoalPositionUseCase,
    private val getGoalPositionsUseCase: GetGoalPositionsUseCase
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
        observePositions()
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

    private fun observePositions() {

        viewModelScope.launch {

            getGoalPositionsUseCase()
                .collect { positions ->

                    _uiState.update { currentState ->

                        currentState.copy(

                            positions = positions.map {

                                GoalNodePosition(
                                    goalNodeId = it.goalId,
                                    x = it.x,
                                    y = it.y
                                )

                            }

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