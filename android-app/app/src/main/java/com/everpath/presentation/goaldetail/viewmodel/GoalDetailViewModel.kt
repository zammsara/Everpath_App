package com.everpath.presentation.goaldetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.model.GoalNode
import com.everpath.domain.usecase.goal.GetGoalNodeByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GoalDetailViewModel(
    private val getGoalNodeByIdUseCase:
    GetGoalNodeByIdUseCase
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

}