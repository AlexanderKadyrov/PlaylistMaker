package com.practicum.playlistmaker.models

import com.practicum.playlistmaker.helpers.codable.Codable

import android.icu.text.SimpleDateFormat

import java.util.Locale

data class Track(
    val trackId: Long,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artworkUrl100: String,
    val primaryGenreName: String,
    val collectionName: String,
    val country: String,
    val releaseDate: String
): Codable {

    fun convertTrackTimeString(): String {
        return SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackTimeMillis)
    }

    fun convertTrackYearString(): String {
        val dateFormatInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val dateFormatOutput = SimpleDateFormat("yyyy", Locale.getDefault())
        val date = dateFormatInput.parse(releaseDate)
        return dateFormatOutput.format(date)
    }

    fun artworkUrl(): String {
        return artworkUrl100.replace("100x100bb.jpg", "512x512bb.jpg")
    }

    companion object {
        const val TRACK_INTENT_EXTRA = "TRACK_INTENT_EXTRA"
    }
}