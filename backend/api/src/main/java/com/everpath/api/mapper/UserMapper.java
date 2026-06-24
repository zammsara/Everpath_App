package com.everpath.api.mapper;

import com.everpath.api.dto.RegisterRequest;
import com.everpath.api.dto.RegisterResponse;
import com.everpath.api.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper encargado de transformar
 * objetos entre la capa DTO
 * y la capa de persistencia.
 *
 * Centraliza todas las conversiones
 * relacionadas con usuarios.
 */
@Component
public class UserMapper {

    /**
     * Convierte un RegisterRequest
     * en una entidad UserEntity.
     *
     * createdAt NO se asigna aquí.
     * id NO se asigna aquí.
     *
     * Ambos son responsabilidad
     * de la capa de negocio.
     */
    public UserEntity toEntity(
            RegisterRequest request
    ) {

        return UserEntity.builder()
                .name(
                        request.getName()
                )
                .email(
                        request.getEmail()
                )
                .password(
                        request.getPassword()
                )
                .build();
    }

    /**
     * Convierte una entidad UserEntity
     * en un RegisterResponse.
     *
     * La contraseña jamás se expone.
     */
    public RegisterResponse toResponse(
            UserEntity user
    ) {

        return RegisterResponse.builder()
                .id(
                        user.getId()
                )
                .name(
                        user.getName()
                )
                .email(
                        user.getEmail()
                )
                .createdAt(
                        user.getCreatedAt()
                )
                .build();
    }
}