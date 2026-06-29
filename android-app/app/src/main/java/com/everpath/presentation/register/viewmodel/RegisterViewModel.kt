package com.everpath.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.data.session.SessionManager
import com.everpath.data.session.UserSession
import com.everpath.domain.sync.SyncManager
import com.everpath.domain.usecase.auth.RegisterUseCase
import com.everpath.presentation.register.state.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de coordinar
 * el proceso de registro de usuarios.
 *
 * Su responsabilidad consiste en:
 *
 * - registrar un nuevo usuario;
 * - validar la confirmación de contraseña;
 * - persistir la sesión;
 * - inicializar UserSession;
 * - exponer el estado de la interfaz.
 */
class RegisterViewModel(

    private val registerUseCase: RegisterUseCase,
    private val sessionManager: SessionManager,
    private val syncManager: SyncManager

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            RegisterUiState()
        )

    val uiState: StateFlow<RegisterUiState> =
        _uiState.asStateFlow()

    /**
     * Ejecuta el proceso de registro.
     */
    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {

        if (password != confirmPassword) {

            _uiState.update {
                it.copy(
                    errorMessage = "Las contraseñas no coinciden."
                )
            }

            return
        }

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {

            val result =
                registerUseCase(
                    name = name,
                    email = email,
                    password = password
                )

            result
                .onSuccess { user ->

                    sessionManager.saveSession(user)
                    UserSession.initialize(user.id)
                    syncManager.refresh()

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isRegistered = true
                        )
                    }

                }
                .onFailure { throwable ->

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage =
                                throwable.message
                                    ?: "No fue posible completar el registro."
                        )
                    }
                }
        }

    }

}