package com.everpath.presentation.today.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase

/**
 * Factory encargada de construir
 * instancias de TodayViewModel.
 */
class TodayViewModelFactory(

    private val getGoalNodesUseCase:
    GetGoalNodesUseCase

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (

            modelClass.isAssignableFrom(
                TodayViewModel::class.java
            )

        ) {

            return TodayViewModel(

                getGoalNodesUseCase =
                    getGoalNodesUseCase

            ) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )

    }

}