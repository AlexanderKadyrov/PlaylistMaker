package com.practicum.playlistmaker.extensions

import com.practicum.playlistmaker.helpers.codable.JSONDecoder
import com.practicum.playlistmaker.helpers.codable.JSONEncoder
import com.practicum.playlistmaker.helpers.codable.Encodable
import com.practicum.playlistmaker.helpers.codable.Decodable

import android.content.Intent

inline fun <reified T: Decodable> Intent.getModel(key: String): T? {
    getStringExtra(key)?.let { value ->
        return JSONDecoder.decode<T>(value)
    }
    return null
}

fun <T: Encodable> Intent.putModel(key: String, model: T) {
    val value = JSONEncoder.encode(model)
    putExtra(key, value)
}