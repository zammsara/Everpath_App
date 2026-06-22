package com.everpath.api.security;

import com.everpath.api.entity.UserEntity;
import com.everpath.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de adaptar
 * UserEntity al sistema interno
 * de autenticación de Spring Security.
 *
 * Permite localizar usuarios
 * mediante su correo electrónico.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl
        implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {

        UserEntity user =
                userRepository.findByEmail(
                                email
                        )
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        "Usuario no encontrado"
                                )
                        );

        return User.builder()

                .username(
                        user.getEmail()
                )

                .password(
                        user.getPassword()
                )

                .authorities(
                        "USER"
                )

                .build();
    }
}