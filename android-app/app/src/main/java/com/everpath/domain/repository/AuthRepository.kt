package com.everpath.domain.repository

import com.everpath.domain.model.User

/**
 * Contrato encargado de definir todas las
 * operaciones relacionadas con autenticación.
 *
 * La implementación concreta será responsable
 * de comunicarse con el backend mediante
 * AuthRemoteDataSource.
 */
interface AuthRepository {


    suspend fun login(
        email: String,
        password: String
    ): User


    suspend fun register(
        name: String,
        email: String,
        password: String
    ): User
}