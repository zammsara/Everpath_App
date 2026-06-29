package com.everpath.domain.usecase.auth

import com.everpath.domain.model.User
import com.everpath.domain.repository.AuthRepository

/**
 * Caso de uso encargado de autenticar
 * un usuario utilizando correo y contraseña.
 *
 * No contiene lógica de presentación.
 * Únicamente coordina la operación
 * de inicio de sesión.
 */
class LoginUseCase(

    private val repository: AuthRepository

) {

    suspend operator fun invoke(

        email: String,

        password: String

    ): Result<User> {

        return repository.login(

            email = email,
            password = password

        )
    }
}