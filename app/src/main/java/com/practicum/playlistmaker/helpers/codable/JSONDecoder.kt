package com.practicum.playlistmaker.helpers.codable

import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

object JSONDecoder {
    inline fun <reified T: Decodable> decode(data: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson(data, type)
    }
}