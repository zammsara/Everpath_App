package com.everpath.presentation.goaldetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.usecase.goal.DeleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodeByIdUseCase
import com.everpath.domain.usecase.goal.UpdateGoalNodeUseCase
import com.everpath.presentation.goaldetail.state.GoalDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalDetailViewModel(
    private val getGoalNodeByIdUseCase: GetGoalNodeByIdUseCase,
    private val updateGoalNodeUseCase: UpdateGoalNodeUseCase,
    private val deleteGoalNodeUseCase: DeleteGoalNodeUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            GoalDetailUiState()
        )

    val uiState: StateFlow<GoalDetailUiState> =
        _uiState.asStateFlow()

    fun loadGoal(
        goalId: String
    ) {

        viewModelScope.launch {

            val goal =
                getGoalNodeByIdUseCase(
                    goalId
                )

            _uiState.update {

                it.copy(
                    goal = goal,
                    isLoading = false
                )

            }

        }

    }

    fun updateGoal(
        title: String,
        description: String
    ) {

        val currentGoal =
            _uiState.value.goal
                ?: return

        val updatedGoal =
            currentGoal.copy(
                title = title,
                description = description
            )

        viewModelScope.launch {

            updateGoalNodeUseCase(
                updatedGoal
            )

            _uiState.update {

                it.copy(
                    goal = updatedGoal
                )

            }

        }

    }

    fun deleteGoal(
        onDeleted: () -> Unit
    ) {

        val currentGoal =
            _uiState.value.goal
                ?: return

        viewModelScope.launch {

            deleteGoalNodeUseCase(
                currentGoal.id
            )

            onDeleted()

        }

    }

}