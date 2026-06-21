package com.everpath.api.controller;

import com.everpath.api.dto.RegisterRequest;
import com.everpath.api.dto.RegisterResponse;
import com.everpath.api.entity.UserEntity;
import com.everpath.api.mapper.UserMapper;
import com.everpath.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de exponer
 * los endpoints relacionados con usuarios
 * y autenticación.
 *
 * Primera versión:
 * Registro de usuarios.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    /**
     * Registra un nuevo usuario.
     *
     * POST /api/v1/auth/register
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse registerUser(

            @Valid
            @RequestBody
            RegisterRequest request

    ) {

        UserEntity userToSave =
                userMapper.toEntity(
                        request
                );

        UserEntity savedUser =
                userService.registerUser(
                        userToSave
                );

        return userMapper.toResponse(
                savedUser
        );
    }
}