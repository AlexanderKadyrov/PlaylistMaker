package com.practicum.playlistmaker.core.data.client

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HTTPClient<S>(
    private val service: S
) {

    fun <R> fetch(
        serviceCallback: (S) -> Call<R>,
        completionCallback: (HTTPClientResult<R>) -> Unit
    ) {
        val call = serviceCallback(service)
        call.enqueue(object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                val body = response.body()
                if (response.isSuccessful) {
                    body?.let {
                        completionCallback(HTTPClientResult.SuccessData(body))
                    } ?: run {
                        completionCallback(HTTPClientResult.SuccessNothing)
                    }
                } else {
                    completionCallback(HTTPClientResult.Error(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                completionCallback(
                    HTTPClientResult.Error(
                        code = -1,
                        message = t.message ?: "HTTPClient throw exception"
                    )
                )
            }
        })
    }
}