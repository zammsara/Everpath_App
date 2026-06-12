package com.everpath.presentation.everpath.viewmodel

import androidx.lifecycle.ViewModel
import com.everpath.presentation.everpath.preview.EverpathMockData
import com.everpath.presentation.everpath.state.EverpathUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EverpathViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        EverpathUiState(
            goalNodes = EverpathMockData.goalNodes
        )
    )

    val uiState: StateFlow<EverpathUiState> =
        _uiState.asStateFlow()
}