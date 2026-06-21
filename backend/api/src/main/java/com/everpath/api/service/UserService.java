package com.everpath.api.service;

import com.everpath.api.entity.UserEntity;

/**
 * Contrato de negocio para
 * operaciones relacionadas con usuarios.
 */
public interface UserService {

    UserEntity registerUser(
            UserEntity user
    );
}