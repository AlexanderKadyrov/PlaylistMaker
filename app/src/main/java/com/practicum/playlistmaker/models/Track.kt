package com.practicum.playlistmaker.models

import android.icu.text.SimpleDateFormat

import java.util.Locale

data class Track(
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artworkUrl100: String
) {
    val trackTimeString: String = SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackTimeMillis)
}