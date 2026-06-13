package com.everpath.presentation.everpath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.enums.LifeAreaType
import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.goal.SaveGoalNodeUseCase
import com.everpath.presentation.everpath.state.EverpathUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class EverpathViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val saveGoalNodeUseCase: SaveGoalNodeUseCase
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

        val goal = GoalNode(
            id = UUID.randomUUID().toString(),
            title = title,
            description = description,
            lifeArea = LifeAreaType.HEALTH,
            status = GoalStatus.ACTIVE,
            activities = emptyList()
        )

        viewModelScope.launch {
            saveGoalNodeUseCase(goal)
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