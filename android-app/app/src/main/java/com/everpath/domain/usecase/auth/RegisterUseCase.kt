package com.everpath.domain.usecase.auth

import com.everpath.domain.model.User
import com.everpath.domain.repository.AuthRepository

/**
 * Caso de uso encargado de registrar
 * un nuevo usuario dentro de Everpath.
 *
 * Su responsabilidad consiste en
 * delegar la operación al repositorio
 * de autenticación.
 */
class RegisterUseCase(
    private val repository: AuthRepository

) {
    suspend operator fun invoke(

        name: String,
        email: String,
        password: String

    ): Result<User> {

        return repository.register(
            name = name,
            email = email,
            password = password

        )
    }
}