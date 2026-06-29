package com.everpath.data.remote.service

import com.everpath.data.remote.dto.auth.LoginRequestDto
import com.everpath.data.remote.dto.auth.LoginResponseDto
import com.everpath.data.remote.dto.auth.RegisterRequestDto
import com.everpath.data.remote.dto.auth.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Servicio encargado de definir
 * las operaciones REST relacionadas
 * con autenticación.
 */
interface AuthApiService {

    @POST("api/v1/auth/login")
    suspend fun login(

        @Body
        request: LoginRequestDto

    ): LoginResponseDto


    @POST("api/v1/auth/register")
    suspend fun register(

        @Body
        request: RegisterRequestDto

    ): RegisterResponseDto

}