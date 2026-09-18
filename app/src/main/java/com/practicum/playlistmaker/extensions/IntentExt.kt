package com.practicum.playlistmaker.extensions

import com.practicum.playlistmaker.helpers.codable.JSONDecoder
import com.practicum.playlistmaker.helpers.codable.JSONEncoder
import com.practicum.playlistmaker.models.IntentExtra

import android.content.Intent

private const val INTENT_EXTRA = "INTENT_EXTRA"

fun Intent.getModel(): IntentExtra? {
    getStringExtra(INTENT_EXTRA)?.let { value ->
        return JSONDecoder.decode<IntentExtra>(value)
    }
    return null
}

fun Intent.putModel(model: IntentExtra) {
    val value = JSONEncoder.encode(model)
    putExtra(INTENT_EXTRA, value)
}