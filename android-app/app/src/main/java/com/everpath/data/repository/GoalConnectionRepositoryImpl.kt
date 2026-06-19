package com.everpath.data.repository

import com.everpath.data.local.dao.GoalConnectionDao
import com.everpath.data.local.mapper.toDomain
import com.everpath.data.local.mapper.toEntity
import com.everpath.domain.model.GoalConnection
import com.everpath.domain.repository.GoalConnectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación del repositorio de conexiones que conecta
 * la capa de dominio con Room mediante GoalConnectionDao.
 */
class GoalConnectionRepositoryImpl(
    private val goalConnectionDao: GoalConnectionDao
) : GoalConnectionRepository {

    override fun getGoalConnections():
            Flow<List<GoalConnection>> {

        return goalConnectionDao
            .getAllConnections()
            .map { connections ->

                connections.map {
                    it.toDomain()
                }

            }
    }

    override suspend fun saveConnection(
        connection: GoalConnection
    ) {

        goalConnectionDao.insertConnection(
            connection.toEntity()
        )
    }

    override suspend fun deleteConnection(
        connectionId: String
    ) {

        goalConnectionDao.deleteConnectionById(
            connectionId
        )
    }
}