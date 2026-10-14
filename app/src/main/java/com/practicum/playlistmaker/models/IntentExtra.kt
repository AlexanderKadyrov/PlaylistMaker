package com.practicum.playlistmaker.models

import com.practicum.playlistmaker.helpers.codable.JSONDecoder
import com.practicum.playlistmaker.helpers.codable.JSONEncoder
import com.practicum.playlistmaker.helpers.codable.Codable

data class IntentExtra(
    private val items: HashMap<String, String> = hashMapOf()
): Codable {

    fun <T: Codable> putModel(key: String, model: T) {
        val json = JSONEncoder.encode(model)
        items[key] = json
    }

    inline fun <reified T: Codable> getModel(key: String): T? {
        (getModelList()[key])?.let { json ->
            val model = JSONDecoder.decode<T>(json)
            return model
        }
        return null
    }

    fun getModelList(): HashMap<String, String> {
        return items
    }
}