package com.everpath.presentation.splash.state

/**
 * Estado de la pantalla Splash.
 *
 * Su responsabilidad consiste en
 * indicar cuándo finalizó la
 * validación de la sesión y hacia
 * qué destino debe navegar la
 * aplicación.
 */
data class SplashUiState(

    /**
     * Indica si la validación de la
     * sesión ya terminó.
     */
    val isLoading: Boolean = true,

    /**
     * Indica si existe una sesión
     * autenticada previamente.
     */
    val hasSession: Boolean = false

)