package com.everpath.data.repository

import com.everpath.data.remote.datasource.AuthRemoteDataSource
import com.everpath.data.remote.dto.auth.LoginRequestDto
import com.everpath.data.remote.dto.auth.RegisterRequestDto
import com.everpath.data.remote.mapper.toDomain
import com.everpath.data.remote.util.safeApiCall
import com.everpath.domain.model.User
import com.everpath.domain.repository.AuthRepository

/**
 * Implementación del repositorio
 * de autenticación.
 *
 * Su responsabilidad consiste en
 * comunicarse con el backend y
 * devolver modelos del dominio.
 */
class AuthRepositoryImpl(

    private val authRemoteDataSource:
    AuthRemoteDataSource

) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<User> {

        return safeApiCall(
            tag = "AuthRepository"
        ) {

            authRemoteDataSource
                .login(
                    LoginRequestDto(
                        email = email,
                        password = password
                    )
                )
                .toDomain()

        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<User> {

        return safeApiCall(
            tag = "AuthRepository"
        ) {

            authRemoteDataSource
                .register(
                    RegisterRequestDto(
                        name = name,
                        email = email,
                        password = password
                    )
                )
                .toDomain()

        }
    }
}