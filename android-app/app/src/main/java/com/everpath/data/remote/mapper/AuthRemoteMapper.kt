package com.everpath.data.remote.mapper

import com.everpath.data.remote.dto.auth.LoginResponseDto
import com.everpath.domain.model.User

/**
 * Mapper encargado de convertir
 * respuestas remotas de autenticación
 * hacia modelos del dominio.
 */
fun LoginResponseDto.toDomain(): User {

    return User(

        id = id,

        name = name,

        email = email

    )

}