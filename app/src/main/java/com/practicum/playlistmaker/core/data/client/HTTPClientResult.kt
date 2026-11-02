package com.practicum.playlistmaker.core.data.client

sealed class HTTPClientResult<out T> {
    data class SuccessData<out T>(val data: T) : HTTPClientResult<T>()
    object SuccessNothing : HTTPClientResult<Nothing>()
    data class Error(val code: Int, val message: String) : HTTPClientResult<Nothing>()
}