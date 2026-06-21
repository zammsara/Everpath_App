package com.everpath.api.controller;

import com.everpath.api.dto.RegisterRequest;
import com.everpath.api.dto.RegisterResponse;
import com.everpath.api.entity.UserEntity;
import com.everpath.api.mapper.UserMapper;
import com.everpath.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de exponer
 * los endpoints relacionados con usuarios
 * y autenticación.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Autenticación",
        description = "Endpoints relacionados con usuarios y autenticación"
)
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Registrar usuario",
            description = "Permite registrar un nuevo usuario dentro de Everpath"
    )
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