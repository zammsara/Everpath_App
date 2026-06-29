package com.everpath.presentation.register.state

/**
 * Estado de la interfaz encargado
 * del registro de nuevos usuarios.
 *
 * Representa toda la información
 * necesaria para que la pantalla
 * pueda renderizarse sin mantener
 * estado adicional.
 */
data class RegisterUiState(

    /**
     * Indica si actualmente se está
     * ejecutando el registro.
     */
    val isLoading: Boolean = false,

    /**
     * Nombre ingresado por el usuario.
     */
    val name: String = "",

    /**
     * Correo electrónico ingresado.
     */
    val email: String = "",

    /**
     * Contraseña ingresada.
     */
    val password: String = "",

    /**
     * Confirmación de contraseña.
     *
     * Esta propiedad pertenece a la
     * capa de presentación, ya que
     * únicamente se utiliza para
     * validar la entrada del usuario.
     */
    val confirmPassword: String = "",

    /**
     * Mensaje de error mostrado
     * durante el registro.
     */
    val errorMessage: String? = null,

    /**
     * Indica que el registro fue
     * completado correctamente.
     *
     * La pantalla utilizará este
     * estado para continuar con
     * el flujo correspondiente.
     */
    val isRegistered: Boolean = false

)