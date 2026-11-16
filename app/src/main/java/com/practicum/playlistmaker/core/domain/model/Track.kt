package com.practicum.playlistmaker.core.domain.model

import android.os.Parcelable
import com.practicum.playlistmaker.core.utils.formatToMinutesAndSeconds
import com.practicum.playlistmaker.core.utils.formatToYear
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable {

    fun convertTrackTimeString(): String {
        return formatToMinutesAndSeconds(trackTimeMillis)
    }

    fun convertTrackYearString(): String? {
        return formatToYear(releaseDate)
    }

    fun artworkUrl(): String {
        return artworkUrl100.replace("100x100bb.jpg", "512x512bb.jpg")
    }

    companion object {
        const val TRACK_INTENT_EXTRA = "TRACK_INTENT_EXTRA"
    }
}