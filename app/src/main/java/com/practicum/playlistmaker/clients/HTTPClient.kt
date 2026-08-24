package com.practicum.playlistmaker.clients

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Response

class HTTPClient(
    private val baseUrl: String
) {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    fun <T>create(service: Class<T>): T {
        return retrofit.create(service)
    }

    suspend fun <T>fetch(callback: suspend () -> Response<T>): HTTPClientResult<T> {
        return try {
            val response = callback()
            val body = response.body()
            if (response.isSuccessful) {
                HTTPClientResult.Success(body)
            } else {
                HTTPClientResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: Exception) {
            HTTPClientResult.Error(code = -1, message = e.message ?: "")
        }
    }
}