package com.everpath.api.service;

import com.everpath.api.entity.UserEntity;
import com.everpath.api.exception.EmailAlreadyExistsException;
import com.everpath.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementación de la lógica
 * de negocio para usuarios.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl
        implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerUser(
            UserEntity user
    ) {

        if (
                userRepository.existsByEmail(
                        user.getEmail()
                )
        ) {
            throw new EmailAlreadyExistsException(
                    user.getEmail()
            );
        }

        user.setPassword(

                passwordEncoder.encode(
                        user.getPassword()
                )

        );

        user.setCreatedAt(
                LocalDateTime.now()
        );

        return userRepository.save(
                user
        );
    }
}