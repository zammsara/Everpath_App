package com.everpath.presentation.quest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.domain.usecase.achievement.EvaluateAchievementsUseCase
import com.everpath.domain.usecase.userprogress.GetUserLevelUseCase
import com.everpath.domain.usecase.userprogress.GetUserProgressUseCase

/**
 * Factory encargada de crear
 * instancias de QuestViewModel.
 */
class QuestViewModelFactory(
    private val getGoalNodesUseCase: GetGoalNodesUseCase,
    private val getUserProgressUseCase: GetUserProgressUseCase,
    private val getUserLevelUseCase: GetUserLevelUseCase,
    private val evaluateAchievementsUseCase: EvaluateAchievementsUseCase

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
                getGoalNodesUseCase =
                    getGoalNodesUseCase,
                getUserProgressUseCase =
                    getUserProgressUseCase,
                getUserLevelUseCase =
                    getUserLevelUseCase,
                evaluateAchievementsUseCase =
                    evaluateAchievementsUseCase

            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}