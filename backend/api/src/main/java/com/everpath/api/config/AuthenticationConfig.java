package com.everpath.api.config;

import com.everpath.api.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Configuración encargada de proporcionar
 * los componentes necesarios para
 * la autenticación mediante Spring Security.
 */
@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserDetailsService userDetailsService;

    private final PasswordConfig passwordConfig;

    /**
     * Provider encargado de autenticar
     * usuarios utilizando base de datos.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        userDetailsService
                );

        provider.setPasswordEncoder(
                passwordConfig.passwordEncoder()
        );

        return provider;
    }


    /**
     * AuthenticationManager utilizado
     * por la capa de servicios para
     * ejecutar autenticaciones.
     */
    @Bean
    public AuthenticationManager authenticationManager(

            AuthenticationConfiguration config

    ) throws Exception {

        return config.getAuthenticationManager();

    }

}