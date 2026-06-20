package com.everpath.presentation.quest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.domain.enums.GoalStatus
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.presentation.quest.state.QuestUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar
 * la información de la pantalla Quest.
 */
class QuestViewModel(
    private val getGoalNodesUseCase: GetGoalNodesUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            QuestUiState()
        )

    val uiState: StateFlow<QuestUiState> =
        _uiState.asStateFlow()

    init {
        loadQuestData()
    }

    private fun loadQuestData() {
        viewModelScope.launch {
            getGoalNodesUseCase()
                .collect { goals ->
                    val activeGoals =
                        goals.filter {
                            it.status == GoalStatus.ACTIVE
                        }

                    val completedGoals =
                        goals.count {
                            it.status == GoalStatus.COMPLETED
                        }

                    _uiState.update {
                        it.copy(
                            activeGoals = activeGoals,
                            completedGoals = completedGoals,
                            isLoading = false
                        )
                    }
                }
        }
    }
}