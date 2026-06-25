package com.everpath.api.service.auth;

import com.everpath.api.dto.auth.LoginRequest;
import com.everpath.api.dto.auth.LoginResponse;

/**
 * Contrato encargado de definir
 * las operaciones relacionadas
 * con autenticación.
 */
public interface AuthService {

    LoginResponse login(
            LoginRequest request
    );

}