package com.everpath.domain.model

/**
 * Modelo de dominio que representa
 * al usuario autenticado dentro
 * de Everpath.
 *
 * Este modelo será utilizado por los
 * casos de uso, ViewModels y la capa
 * de presentación, evitando depender
 * directamente de los DTOs remotos.
 */
data class User(

    val id: Long,

    val name: String,

    val email: String

)