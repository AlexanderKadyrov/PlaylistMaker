package com.practicum.playlistmaker.clients

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Call

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

    fun <R>fetch(
        serviceCallback: (S) -> Call<R>,
        completionCallback: (HTTPClientResult<R>) -> Unit
    ) {
        val call = serviceCallback(service)
        call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                val body = response.body()
                if (response.isSuccessful) {
                    completionCallback(HTTPClientResult.Success(body))
                } else {
                    completionCallback(HTTPClientResult.Error(response.code(), response.message()))
                }
            }
            override fun onFailure(call: Call<R>, t: Throwable) {
                completionCallback(HTTPClientResult.Error(code = -1, message = t.message ?: "HTTPClient throw exception"))
            }
        })
    }
}

inline fun <reified S>HTTPClient(baseUrl: String): HTTPClient<S> {
    return HTTPClient(S::class.java, baseUrl)
}