package com.everpath.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everpath.data.session.SessionManager
import com.everpath.data.session.UserSession
import com.everpath.domain.sync.SyncManager
import com.everpath.domain.usecase.auth.LoginUseCase
import com.everpath.presentation.login.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de coordinar
 * el proceso de inicio de sesión.
 *
 * Su responsabilidad consiste en:
 *
 * - autenticar al usuario;
 * - persistir la sesión;
 * - inicializar UserSession;
 * - exponer el estado de la interfaz.
 */
class LoginViewModel(

    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager,
    private val syncManager: SyncManager

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            LoginUiState()
        )

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()


    /**
     * Actualiza el correo electrónico
     * introducido por el usuario.
     */
    fun onEmailChanged(
        email: String
    ) {

        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    /**
     * Actualiza la contraseña
     * introducida por el usuario.
     */
    fun onPasswordChanged(
        password: String
    ) {

        _uiState.update {
            it.copy(
                password = password
            )
        }
    }


    /**
     * Ejecuta el proceso de autenticación.
     */
    fun login(
        email: String,
        password: String
    ) {

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {

            val result =
                loginUseCase(
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
                            isLoggedIn = true
                        )
                    }

                }
                .onFailure { throwable ->

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage =
                                throwable.message
                                    ?: "No fue posible iniciar sesión."
                        )
                    }

                }

        }

    }

}