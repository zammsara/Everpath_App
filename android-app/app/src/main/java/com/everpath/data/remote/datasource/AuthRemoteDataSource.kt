package com.everpath.data.remote.datasource

import com.everpath.data.remote.dto.auth.LoginRequestDto
import com.everpath.data.remote.dto.auth.LoginResponseDto
import com.everpath.data.remote.dto.auth.RegisterRequestDto
import com.everpath.data.remote.dto.auth.RegisterResponseDto
import com.everpath.data.remote.service.AuthApiService

/**
 * DataSource remoto encargado
 * de encapsular todas las llamadas
 * HTTP relacionadas con autenticación.
 */
class AuthRemoteDataSource(

    private val authApiService: AuthApiService

) {

    suspend fun login(

        request: LoginRequestDto

    ): LoginResponseDto {

        return authApiService.login(request)

    }


    suspend fun register(

        request: RegisterRequestDto

    ): RegisterResponseDto {

        return authApiService.register(request)

    }

}