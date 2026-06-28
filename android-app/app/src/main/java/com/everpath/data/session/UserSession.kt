package com.everpath.data.session

/**
 * Mantiene la información de la sesión
 * actualmente utilizada por la aplicación.
 *
 * Durante esta etapa del proyecto existe
 * un único usuario de pruebas, por lo que
 * el identificador permanece fijo.
 *
 * En futuras versiones este objeto será
 * actualizado automáticamente al iniciar
 * sesión mediante autenticación.
 */
object UserSession {

    /**
     * Identificador del usuario
     * actualmente autenticado.
     */
    var userId: Long = 7L

}