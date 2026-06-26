package com.everpath.data.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Objeto encargado de construir
 * y configurar el cliente HTTP
 * utilizado por Retrofit.
 *
 * Toda la configuración relacionada
 * con conexiones, logs, tiempos de espera
 * y futuros interceptores deberá
 * centralizarse aquí.
 */
object NetworkClient {

    /**
     * Interceptor encargado de mostrar
     * en Logcat todas las peticiones
     * y respuestas HTTP durante
     * el desarrollo.
     */
    private val loggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Instancia única de OkHttpClient
     * utilizada por toda la aplicación.
     */
    val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()

            .addInterceptor(
                loggingInterceptor
            )

            .connectTimeout(
                30,
                TimeUnit.SECONDS
            )

            .readTimeout(
                30,
                TimeUnit.SECONDS
            )

            .writeTimeout(
                30,
                TimeUnit.SECONDS
            )

            .build()

}