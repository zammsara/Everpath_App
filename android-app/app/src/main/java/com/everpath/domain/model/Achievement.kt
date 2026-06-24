package com.everpath.domain.model

/**
 * Representa un logro desbloqueable
 * dentro del sistema de progresión
 * de Everpath.
 *
 * Los achievements funcionan como
 * hitos importantes alcanzados por
 * el usuario durante su recorrido.
 */
data class Achievement(

    val id: String,

    val title: String,

    val description: String,

    val unlocked: Boolean,

    val unlockedAt: Long?

)