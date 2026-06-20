package com.everpath.presentation.today.viewmodel

import androidx.lifecycle.ViewModel
import com.everpath.domain.usecase.goal.GetGoalNodesUseCase
import com.everpath.presentation.today.state.TodayUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel responsable del Dashboard.
 *
 * Gestiona las métricas generales
 * y el resumen de progreso del usuario.
 */
class TodayViewModel(

    private val getGoalNodesUseCase:
    GetGoalNodesUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            TodayUiState()
        )

    val uiState: StateFlow<TodayUiState> =
        _uiState.asStateFlow()

}