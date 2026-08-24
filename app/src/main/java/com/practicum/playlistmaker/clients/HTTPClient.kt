package com.practicum.playlistmaker.clients

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Response

class HTTPClient<S>(
    private val serviceClass: Class<S>,
    private val baseUrl: String
) {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val service: S by lazy {
        retrofit.create(serviceClass)
    }

    suspend fun <R>fetch(callback: suspend (S) -> Response<R>): HTTPClientResult<R> {
        return try {
            val response = callback(service)
            val body = response.body()
            if (response.isSuccessful) {
                HTTPClientResult.Success(body)
            } else {
                HTTPClientResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: Exception) {
            HTTPClientResult.Error(code = -1, message = e.message ?: "HTTPClient throw exception")
        }
    }
}

inline fun <reified S>HTTPClient(baseUrl: String): HTTPClient<S> {
    return HTTPClient(S::class.java, baseUrl)
}