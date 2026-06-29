package com.everpath.data.remote.dto.auth


/**
 * DTO recibido después
 * del registro exitoso.
 */
data class RegisterResponseDto(

    val id: Long,

    val name: String,

    val email: String,

    val createdAt: String

)