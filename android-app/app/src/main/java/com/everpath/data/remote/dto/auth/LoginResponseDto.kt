package com.everpath.data.remote.dto.auth


/**
 * DTO recibido después de
 * un inicio de sesión exitoso.
 */
data class LoginResponseDto(

    val id: Long,

    val name: String,

    val email: String

)