package com.everpath.domain.sync

/**
 * Contrato encargado de coordinar la
 * sincronización completa entre el
 * backend y la base de datos local.
 *
 * Cada implementación será responsable
 * de actualizar todas las entidades
 * necesarias respetando la estrategia
 * Offline First.
 */
interface SyncManager {

    /**
     * Sincroniza completamente la
     * información del usuario.
     */
    suspend fun refresh()

}