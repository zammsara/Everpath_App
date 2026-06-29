package com.everpath.domain.repository

import com.everpath.domain.model.User

/**
 * Contrato encargado de gestionar
 * todas las operaciones relacionadas
 * con autenticación.
 */
interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<User>

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<User>
}