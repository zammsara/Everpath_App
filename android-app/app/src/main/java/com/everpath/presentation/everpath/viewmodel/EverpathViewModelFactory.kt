package com.everpath.presentation.everpath.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.goal.SaveGoalNodeUseCase
import com.everpath.domain.usecase.goalposition.SaveGoalPositionUseCase

class EverpathViewModelFactory(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val saveGoalNodeUseCase: SaveGoalNodeUseCase,
    private val saveGoalPositionUseCase: SaveGoalPositionUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                EverpathViewModel::class.java
            )
        ) {

            return EverpathViewModel(
                getGoalNodesUseCase = getGoalNodesUseCase,
                saveGoalNodeUseCase = saveGoalNodeUseCase,
                saveGoalPositionUseCase = saveGoalPositionUseCase
            ) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}