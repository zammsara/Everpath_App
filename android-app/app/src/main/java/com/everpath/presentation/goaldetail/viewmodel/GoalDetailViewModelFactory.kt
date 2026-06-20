package com.everpath.presentation.goaldetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.DeleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodeByIdUseCase
import com.everpath.domain.usecase.goal.UpdateGoalNodeUseCase
import com.everpath.domain.usecase.goal.CompleteGoalNodeUseCase

class GoalDetailViewModelFactory(

    private val getGoalNodeByIdUseCase: GetGoalNodeByIdUseCase,
    private val updateGoalNodeUseCase: UpdateGoalNodeUseCase,
    private val completeGoalNodeUseCase: CompleteGoalNodeUseCase,
    private val deleteGoalNodeUseCase: DeleteGoalNodeUseCase

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return GoalDetailViewModel(

            getGoalNodeByIdUseCase = getGoalNodeByIdUseCase,
            updateGoalNodeUseCase = updateGoalNodeUseCase,
            completeGoalNodeUseCase = completeGoalNodeUseCase,
            deleteGoalNodeUseCase = deleteGoalNodeUseCase

        ) as T

    }
}