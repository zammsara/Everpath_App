package com.everpath.presentation.today.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.userprogress.GetLevelProgressUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase

/**
 * Factory encargada de construir
 * instancias de TodayViewModel.
 */
class TodayViewModelFactory(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val getUserLevelUseCase: GetUserLevelUseCase,
    private val getLevelProgressUseCase: GetLevelProgressUseCase

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
                getUserLevelUseCase = getUserLevelUseCase,
                getLevelProgressUseCase = getLevelProgressUseCase
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )

    }

}