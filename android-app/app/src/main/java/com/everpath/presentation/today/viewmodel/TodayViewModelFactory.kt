package com.everpath.presentation.today.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase

/**
 * Factory encargada de construir
 * instancias de TodayViewModel.
 */
class TodayViewModelFactory(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val getUserLevelUseCase: GetUserLevelUseCase

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
                getGoalNodesUseCase = getGoalNodesUseCase,
                getUserProgressUseCase = getUserProgressUseCase,
                getUserLevelUseCase = getUserLevelUseCase
            ) as T

        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )

    }

}