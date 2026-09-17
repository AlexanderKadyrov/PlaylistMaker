package com.practicum.playlistmaker.models

import com.practicum.playlistmaker.helpers.codable.Codable

import androidx.collection.ArrayMap

data class IntentExtra(
    private val items: ArrayMap<String, Codable> = ArrayMap<String, Codable>()
): Codable {

    fun <T: Codable> putExtra(key: String, model: T) {
        items[key] = model
    }

    inline fun <reified T: Codable> getExtra(key: String): T {
        return getExtraList()[key] as T
    }

    fun getExtraList(): ArrayMap<String, Codable> {
        return items
    }
}