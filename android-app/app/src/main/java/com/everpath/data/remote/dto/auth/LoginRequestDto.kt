package com.everpath.data.remote.dto.auth

/**
 * DTO utilizado para enviar las credenciales
 * durante el inicio de sesión.
 */
data class LoginRequestDto(

    val email: String,

    val password: String

)