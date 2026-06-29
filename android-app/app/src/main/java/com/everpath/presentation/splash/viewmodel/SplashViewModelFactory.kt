package com.everpath.presentation.splash.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.everpath.domain.usecase.auth.RestoreSessionUseCase

/**
 * Factory encargada de crear
 * instancias de SplashViewModel
 * con sus dependencias.
 */
class SplashViewModelFactory(

    private val restoreSessionUseCase:
    RestoreSessionUseCase

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>

    ): T {

        if (
            modelClass.isAssignableFrom(
                SplashViewModel::class.java
            )

        ) {

            return SplashViewModel(

                restoreSessionUseCase =
                    restoreSessionUseCase

            ) as T

        }

        throw IllegalArgumentException(
            "ViewModel desconocido."
        )
    }
}