package com.everpath.data.session

/**
 * Mantiene la información mínima
 * de la sesión actualmente activa.
 *
 * El valor se inicializa durante el
 * arranque de la aplicación a partir
 * de SessionManager y posteriormente
 * puede actualizarse después de un
 * inicio o cierre de sesión.
 */
object UserSession {

    private var currentUserId: Long? = null

    /**
     * Identificador del usuario
     * autenticado actualmente.
     */
    val userId: Long
        get() = currentUserId
            ?: error(
                "UserSession no ha sido inicializada."
            )

    /**
     * Inicializa la sesión activa.
     */
    fun initialize(
        userId: Long
    ) {
        currentUserId =
            userId
    }


    fun isInitialized():
            Boolean {
        return currentUserId != null

    }


    fun clear() {
        currentUserId = null
    }
}