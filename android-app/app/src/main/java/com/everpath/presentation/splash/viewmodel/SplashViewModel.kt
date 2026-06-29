package com.everpath.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.everpath.domain.usecase.auth.RestoreSessionUseCase
import com.everpath.presentation.splash.state.SplashUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel encargado de coordinar
 * el proceso de restauración de
 * la sesión del usuario.
 *
 * Su responsabilidad consiste en:
 *
 * - verificar si existe una sesión;
 * - restaurar UserSession;
 * - exponer el estado de la interfaz.
 */
class SplashViewModel(

    private val restoreSessionUseCase:
    RestoreSessionUseCase

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            SplashUiState()
        )

    val uiState:
            StateFlow<SplashUiState> =
        _uiState.asStateFlow()

    init {

        restoreSession()

    }

    /**
     * Ejecuta la restauración
     * de la sesión persistida.
     */
    private fun restoreSession() {

        val hasSession =
            restoreSessionUseCase()

        _uiState.update {

            it.copy(
                isLoading = false,
                hasSession = hasSession
            )

        }

    }

}