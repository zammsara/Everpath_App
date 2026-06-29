package com.everpath.data.remote.dto.auth


/**
 * DTO utilizado para registrar
 * un nuevo usuario.
 */
data class RegisterRequestDto(

    val name: String,

    val email: String,

    val password: String

)