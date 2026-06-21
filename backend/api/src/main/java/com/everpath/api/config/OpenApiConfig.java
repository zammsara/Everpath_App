package com.everpath.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI
 * y documentación Swagger
 * para la API de Everpath.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title(
                                        "Everpath API"
                                )
                                .description(
                                        "Backend oficial de Everpath"
                                )
                                .version(
                                        "1.0"
                                )
                );
    }
}