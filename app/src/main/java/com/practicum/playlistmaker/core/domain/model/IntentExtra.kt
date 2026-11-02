package com.practicum.playlistmaker.core.domain.model

import com.practicum.playlistmaker.core.helpers.codable.Codable
import com.practicum.playlistmaker.core.helpers.codable.JSONDecoder
import com.practicum.playlistmaker.core.helpers.codable.JSONEncoder

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