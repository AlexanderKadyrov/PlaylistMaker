package com.practicum.playlistmaker.core.data.client

sealed class HTTPClientResult<out T> {
    data class Success<out T>(val data: T?): HTTPClientResult<T>()
    data class Error(val code: Int, val message: String): HTTPClientResult<Nothing>()
}