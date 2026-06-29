package com.everpath.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.data.session.UserSession
import com.everpath.domain.usecase.auth.RestoreSessionUseCase
import com.everpath.presentation.splash.state.SplashUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de restaurar
 * la sesión del usuario al iniciar la app.
 */
class SplashViewModel(

    private val restoreSessionUseCase: RestoreSessionUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(SplashUiState())

    val uiState: StateFlow<SplashUiState> =
        _uiState.asStateFlow()

    init {
        checkSession()
    }

    /**
     * Verifica si existe sesión guardada
     * y la restaura si es posible.
     */
    private fun checkSession() {

        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {

            val hasSession =
                restoreSessionUseCase()

            if (hasSession) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasSession = true
                    )
                }

            } else {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasSession = false
                    )
                }

            }

        }
    }
}