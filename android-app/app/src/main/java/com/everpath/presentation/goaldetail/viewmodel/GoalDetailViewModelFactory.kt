package com.everpath.presentation.goaldetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.DeleteGoalNodeUseCase
import com.everpath.domain.usecase.goal.GetGoalNodeByIdUseCase
import com.everpath.domain.usecase.goal.UpdateGoalNodeUseCase

/**
 * Factory encargada de crear
 * instancias de GoalDetailViewModel.
 */
class GoalDetailViewModelFactory(

    private val getGoalNodeByIdUseCase:
    GetGoalNodeByIdUseCase,

    private val updateGoalNodeUseCase:
    UpdateGoalNodeUseCase,

    private val deleteGoalNodeUseCase:
    DeleteGoalNodeUseCase

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return GoalDetailViewModel(

            getGoalNodeByIdUseCase =
                getGoalNodeByIdUseCase,

            updateGoalNodeUseCase =
                updateGoalNodeUseCase,

            deleteGoalNodeUseCase =
                deleteGoalNodeUseCase

        ) as T
    }
}