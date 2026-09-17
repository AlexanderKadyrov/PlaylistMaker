package com.practicum.playlistmaker.models

import com.practicum.playlistmaker.helpers.codable.Codable

import androidx.collection.ArrayMap

data class IntentExtra(
    private val items: ArrayMap<String, Codable> = ArrayMap<String, Codable>()
): Codable {

    fun <T: Codable> putModel(key: String, model: T) {
        items[key] = model
    }

    inline fun <reified T: Codable> getModel(key: String): T {
        return getModelList()[key] as T
    }

    fun getModelList(): ArrayMap<String, Codable> {
        return items
    }
}