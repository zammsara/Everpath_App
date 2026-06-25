package com.everpath.api.service.user;

import com.everpath.api.entity.UserEntity;
import com.everpath.api.entity.UserProgressEntity;
import com.everpath.api.exception.EmailAlreadyExistsException;
import com.everpath.api.repository.UserProgressRepository;
import com.everpath.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserProgressRepository userProgressRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
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

        UserEntity savedUser =
                userRepository.save(
                        user
                );


        UserProgressEntity progress =
                UserProgressEntity.builder()

                        .user(
                                savedUser
                        )

                        .xp(
                                0
                        )

                        .build();


        userProgressRepository.save(
                progress
        );


        return savedUser;
    }

    @Override
    @Transactional
    public void deleteUser(
            Long userId
    ) {

        UserEntity user =
                userRepository.findById(
                                userId
                        )
                        .orElseThrow(
                                () ->
                                        new EntityNotFoundException(
                                                "Usuario no encontrado"
                                        )
                        );

        userRepository.delete(
                user
        );
    }
}