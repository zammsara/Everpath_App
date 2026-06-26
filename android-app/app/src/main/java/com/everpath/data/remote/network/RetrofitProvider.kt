package com.everpath.data.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto encargado de construir
 * la instancia única de Retrofit
 * utilizada por la capa remota
 * de Everpath.
 *
 * Centraliza la configuración
 * relacionada con serialización
 * y comunicación HTTP.
 */
object RetrofitProvider {

    /**
     * Instancia única de Retrofit
     * utilizada por todos los
     * servicios REST.
     */
    val retrofit: Retrofit =
        Retrofit.Builder()

            .baseUrl(
                ApiConstants.BASE_URL
            )

            .client(
                NetworkClient.okHttpClient
            )

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

}