package com.everpath.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.data.session.SessionManager
import com.everpath.domain.usecase.auth.LoginUseCase

/**
 * Factory encargada de crear
 * instancias de LoginViewModel.
 *
 * Permite inyectar las dependencias
 * requeridas por el ViewModel
 * respetando la arquitectura de
 * Android y Compose.
 */
class LoginViewModelFactory(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>

    ): T {

        if (

            modelClass.isAssignableFrom(
                LoginViewModel::class.java
            )

        ) {

            return LoginViewModel(
                loginUseCase = loginUseCase,
                sessionManager = sessionManager

            ) as T

        }

        throw IllegalArgumentException(

            "ViewModel desconocido."

        )
    }
}