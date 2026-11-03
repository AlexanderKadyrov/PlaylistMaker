package com.practicum.playlistmaker.features.search.data.mapper

import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.features.search.data.network.TrackDto

class TrackSearchMapper {

    fun mapListDto(listDto: List<TrackDto>): List<Track> {
        return listDto.map { itemDto ->
            mapItemDto(itemDto)
        }
    }

    fun mapItemDto(itemDto: TrackDto): Track {
        return Track(
            trackId = itemDto.trackId,
            trackName = itemDto.trackName,
            artistName = itemDto.artistName,
            trackTimeMillis = itemDto.trackTimeMillis,
            artworkUrl100 = itemDto.artworkUrl100,
            primaryGenreName = itemDto.primaryGenreName,
            previewUrl = itemDto.previewUrl,
            country = itemDto.country,
            collectionName = itemDto.collectionName,
            releaseDate = itemDto.releaseDate
        )
    }
}