package com.everpath.domain.usecase.database

import com.everpath.data.local.database.EverpathDatabase

/**
 * Caso de uso encargado de limpiar
 * completamente la base de datos local.
 *
 * Se utiliza durante el cierre de sesión
 * para evitar fugas de información entre
 * usuarios.
 */
class ClearLocalDatabaseUseCase(

    private val database: EverpathDatabase

) {

    suspend operator fun invoke() {

        database.clearAllTables()

    }

}