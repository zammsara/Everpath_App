package com.everpath.presentation.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.data.session.SessionManager
import com.everpath.domain.usecase.auth.RegisterUseCase

/**
 * Factory encargada de crear
 * instancias de RegisterViewModel.
 *
 * Permite desacoplar la creación
 * del ViewModel de la capa de
 * presentación.
 */
class RegisterViewModelFactory(

    private val registerUseCase: RegisterUseCase,

    private val sessionManager: SessionManager

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>

    ): T {

        if (
            modelClass.isAssignableFrom(
                RegisterViewModel::class.java
            )

        ) {

            return RegisterViewModel(
                registerUseCase = registerUseCase,
                sessionManager = sessionManager

            ) as T

        }

        throw IllegalArgumentException(
            "ViewModel desconocido."

        )

    }

}