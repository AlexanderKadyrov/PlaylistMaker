package com.practicum.playlistmaker.core.extensions.codable

import com.google.gson.Gson

object JSONEncoder {
    fun <T: Encodable> encode(model: T): String {
        return Gson().toJson(model)
    }
}