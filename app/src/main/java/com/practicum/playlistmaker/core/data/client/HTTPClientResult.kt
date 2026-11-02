package com.practicum.playlistmaker.core.data.client

sealed class HTTPClientResult<out T> {
    data class SuccessBody<out T>(val data: T) : HTTPClientResult<T>()
    object SuccessEmpty : HTTPClientResult<Nothing>()
    data class Error(val code: Int, val message: String) : HTTPClientResult<Nothing>()
}