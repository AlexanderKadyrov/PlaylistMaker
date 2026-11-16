package com.practicum.playlistmaker.core.domain.model

import com.practicum.playlistmaker.core.helpers.codable.Codable
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

data class Track(
    val trackId: Long,
    val trackName: String,
    val artistName: String,
    val trackTimeMillis: Int,
    val artworkUrl100: String,
    val primaryGenreName: String,
    val previewUrl: String,
    val country: String,
    val collectionName: String?,
    val releaseDate: String?
) : Codable {

    fun convertTrackTimeString(): String {
        val formatter = SimpleDateFormat("mm:ss", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        return formatter.format(trackTimeMillis)
    }

    fun convertTrackYearString(): String? {
        return releaseDate?.let { value ->
            val dateFormatInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val dateFormatOutput = SimpleDateFormat("yyyy", Locale.getDefault())
            val date = dateFormatInput.parse(value)
            date?.let { dateFormatOutput.format(it) }
        }
    }

    fun artworkUrl(): String {
        return artworkUrl100.replace("100x100bb.jpg", "512x512bb.jpg")
    }

    companion object {
        const val TRACK_INTENT_EXTRA = "TRACK_INTENT_EXTRA"
    }
}