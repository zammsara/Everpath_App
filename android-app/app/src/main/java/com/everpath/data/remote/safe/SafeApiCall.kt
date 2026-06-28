package com.everpath.data.remote.util

import android.util.Log
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Ejecuta llamadas HTTP de forma segura.
 *
 * Centraliza el manejo de errores de red para
 * implementar la estrategia Offline First,
 * permitiendo que el repositorio utilice la
 * información almacenada en Room cuando el
 * backend no esté disponible.
 */
suspend inline fun <T> safeApiCall(
    tag: String,
    crossinline apiCall: suspend () -> T
): Result<T> {

    return try {

        Result.success(
            apiCall()
        )

    } catch (exception: UnknownHostException) {

        Log.w(
            tag,
            "No fue posible conectarse al servidor.",
            exception
        )

        Result.failure(exception)

    } catch (exception: SocketTimeoutException) {

        Log.w(
            tag,
            "La conexión con el servidor excedió el tiempo de espera.",
            exception
        )

        Result.failure(exception)

    } catch (exception: IOException) {

        Log.w(
            tag,
            "Ocurrió un error de red durante la comunicación con el servidor.",
            exception
        )

        Result.failure(exception)

    } catch (exception: HttpException) {

        Log.w(
            tag,
            when (exception.code()) {

                401 -> "No autorizado para acceder al recurso."

                404 -> "El recurso solicitado no existe."

                500 -> "El servidor presentó un error interno."

                else ->
                    "Error HTTP ${exception.code()}."
            },
            exception
        )

        Result.failure(exception)
    }
}