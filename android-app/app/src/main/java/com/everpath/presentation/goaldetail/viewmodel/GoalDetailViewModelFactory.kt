package com.everpath.presentation.goaldetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodeByIdUseCase

class GoalDetailViewModelFactory(
    private val getGoalNodeByIdUseCase:
    GetGoalNodeByIdUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return GoalDetailViewModel(
            getGoalNodeByIdUseCase
        ) as T

    }

}