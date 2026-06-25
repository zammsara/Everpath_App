package com.everpath.api.service.auth;

import com.everpath.api.dto.auth.LoginRequest;
import com.everpath.api.dto.auth.LoginResponse;
import com.everpath.api.entity.UserEntity;
import com.everpath.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de autenticar
 * usuarios utilizando Spring Security.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    @Override
    public LoginResponse login(
            LoginRequest request
    ) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );

        UserEntity user =
                userRepository.findByEmail(
                                request.getEmail()
                        )
                        .orElseThrow();

        return LoginResponse.builder()

                .id(
                        user.getId()
                )

                .name(
                        user.getName()
                )

                .email(
                        user.getEmail()
                )

                .build();
    }
}