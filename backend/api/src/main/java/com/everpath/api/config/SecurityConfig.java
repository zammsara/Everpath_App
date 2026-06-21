package com.everpath.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración temporal de seguridad.
 *
 * Permite acceder libremente a los
 * endpoints públicos mientras se
 * implementa JWT.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(

            HttpSecurity http

    ) throws Exception {

        return http

                .csrf(
                        csrf -> csrf.disable()
                )

                .authorizeHttpRequests(
                        auth -> auth

                                .requestMatchers(
                                        "/api/v1/auth/**"
                                )
                                .permitAll()

                                .anyRequest()
                                .authenticated()
                )

                .httpBasic(
                        Customizer.withDefaults()
                )

                .build();
    }

}