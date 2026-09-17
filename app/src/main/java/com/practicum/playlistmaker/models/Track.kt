package com.practicum.playlistmaker.models

import com.practicum.playlistmaker.helpers.codable.Codable

import android.icu.text.SimpleDateFormat

import java.util.Locale

data class Track(
    val trackId: Long,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artworkUrl100: String
): Codable {

    fun convertTrackTimeString(): String {
        return SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackTimeMillis)
    }

    companion object {
        const val TRACK_INTENT_EXTRA = "TRACK_INTENT_EXTRA"
    }
}