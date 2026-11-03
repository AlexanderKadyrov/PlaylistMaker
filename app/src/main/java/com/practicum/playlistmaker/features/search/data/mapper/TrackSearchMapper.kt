package com.practicum.playlistmaker.features.search.data.mapper

import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.features.search.data.network.TrackDto

class TrackSearchMapper {

    fun map(from: List<TrackDto>): List<Track> = from.map { map(it) }

    fun map(from: TrackDto): Track {
        return Track(
            trackId = from.trackId,
            trackName = from.trackName,
            artistName = from.artistName,
            trackTimeMillis = from.trackTimeMillis,
            artworkUrl100 = from.artworkUrl100,
            primaryGenreName = from.primaryGenreName,
            previewUrl = from.previewUrl,
            country = from.country,
            collectionName = from.collectionName,
            releaseDate = from.releaseDate
        )
    }

    fun mapToDto(from: List<Track>): List<TrackDto> = from.map { mapToDto(it) }

    fun mapToDto(from: Track): TrackDto {
        return TrackDto(
            trackId = from.trackId,
            trackName = from.trackName,
            artistName = from.artistName,
            trackTimeMillis = from.trackTimeMillis,
            artworkUrl100 = from.artworkUrl100,
            primaryGenreName = from.primaryGenreName,
            previewUrl = from.previewUrl,
            country = from.country,
            collectionName = from.collectionName,
            releaseDate = from.releaseDate
        )
    }
}