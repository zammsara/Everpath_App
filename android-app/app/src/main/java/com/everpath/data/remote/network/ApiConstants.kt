package com.everpath.data.remote.network

/**
 * Archivo encargado de centralizar
 * todas las constantes utilizadas
 * por la infraestructura de red
 * de Everpath.
 *
 * Mantener estos valores en un único
 * lugar facilita futuras migraciones
 * entre entornos de desarrollo,
 * pruebas y producción.
 */
object ApiConstants {

    /**
     * URL base del servidor
     * Spring Boot.
     *
     * Durante el desarrollo
     * utilizará el servidor local.
     */
    const val BASE_URL =
        "http://10.0.2.2:8080/"

}