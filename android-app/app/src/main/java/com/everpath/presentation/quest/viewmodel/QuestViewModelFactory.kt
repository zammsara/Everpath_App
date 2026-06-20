package com.everpath.presentation.quest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase

/**
 * Factory encargada de crear
 * instancias de QuestViewModel.
 */
class QuestViewModelFactory(
    private val getGoalNodesUseCase: GetGoalNodesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                QuestViewModel::class.java
            )
        ) {

            return QuestViewModel(
                getGoalNodesUseCase = getGoalNodesUseCase
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}