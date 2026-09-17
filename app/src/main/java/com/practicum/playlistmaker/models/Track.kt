package com.practicum.playlistmaker.models

import com.practicum.playlistmaker.helpers.codable.Decodable
import com.practicum.playlistmaker.helpers.codable.Encodable

import android.icu.text.SimpleDateFormat

import java.util.Locale

data class Track(
    val trackId: Long,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artworkUrl100: String
): Decodable, Encodable {
    fun convertTrackTimeString(): String {
        return SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackTimeMillis)
    }
}