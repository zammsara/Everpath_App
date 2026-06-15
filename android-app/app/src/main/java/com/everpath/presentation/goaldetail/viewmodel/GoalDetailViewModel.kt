package com.everpath.presentation.goaldetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.goal.DeleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodeByIdUseCase
import com.everpath.domain.usecase.goal.UpdateGoalNodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GoalDetailViewModel(
    private val getGoalNodeByIdUseCase:GetGoalNodeByIdUseCase,
    private val updateGoalNodeUseCase:UpdateGoalNodeUseCase,
    private val deleteGoalNodeUseCase:DeleteGoalNodeUseCase
) : ViewModel() {

    private val _goal =
        MutableStateFlow<GoalNode?>(null)

    val goal: StateFlow<GoalNode?> =
        _goal

    fun loadGoal(
        goalId: String
    ) {

        viewModelScope.launch {

            _goal.value =
                getGoalNodeByIdUseCase(
                    goalId
                )

        }

    }

    fun updateGoal(
        title: String,
        description: String
    ) {

        val currentGoal =
            _goal.value
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

            _goal.value =
                updatedGoal

        }

    }

    fun deleteGoal(
        onDeleted: () -> Unit
    ) {

        val currentGoal =
            _goal.value
                ?: return

        viewModelScope.launch {

            deleteGoalNodeUseCase(
                currentGoal.id
            )

            onDeleted()

        }

    }

}