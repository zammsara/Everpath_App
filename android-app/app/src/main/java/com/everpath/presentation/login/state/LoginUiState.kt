package com.everpath.presentation.login.state

/**
 * Estado de la pantalla de inicio de sesión.
 *
 * Representa toda la información que la interfaz
 * necesita para renderizarse.
 *
 * La UI nunca almacenará estado propio relacionado
 * con la autenticación; únicamente observará una
 * instancia de esta clase proporcionada por el
 * LoginViewModel.
 */
data class LoginUiState(

    /**
     * Correo electrónico ingresado
     * por el usuario.
     */
    val email: String = "",

    /**
     * Contraseña ingresada
     * por el usuario.
     */
    val password: String = "",

    /**
     * Indica si actualmente se está
     * ejecutando una operación de login.
     *
     * Permitirá mostrar un indicador
     * de carga y deshabilitar los
     * controles de la pantalla.
     */
    val isLoading: Boolean = false,

    /**
     * Mensaje de error que será mostrado
     * por la interfaz cuando el proceso
     * de autenticación falle.
     *
     * Si es null significa que no existe
     * ningún error para mostrar.
     */
    val errorMessage: String? = null,

    /**
     * Indica que el usuario inició sesión
     * correctamente.
     *
     * La pantalla utilizará este valor
     * para iniciar la navegación hacia
     * la aplicación principal.
     */
    val isLoggedIn: Boolean = false

)