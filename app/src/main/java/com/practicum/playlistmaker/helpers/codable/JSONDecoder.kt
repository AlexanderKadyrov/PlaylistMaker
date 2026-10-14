package com.practicum.playlistmaker.helpers.codable

import com.google.gson.Gson

object JSONDecoder {
    inline fun <reified T: Decodable> decode(data: String): T? {
        return Gson().fromJson(data, T::class.java)
    }
}