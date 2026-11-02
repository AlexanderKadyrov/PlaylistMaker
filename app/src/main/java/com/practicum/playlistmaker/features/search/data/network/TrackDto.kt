package com.practicum.playlistmaker.features.search.data.network

data class TrackDto(
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
)
