package com.everpath.api.service;

import com.everpath.api.dto.LoginRequest;
import com.everpath.api.dto.LoginResponse;

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