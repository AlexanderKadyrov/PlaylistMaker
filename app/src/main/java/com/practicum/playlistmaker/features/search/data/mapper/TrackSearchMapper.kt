package com.practicum.playlistmaker.features.search.data.mapper

import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.features.search.data.network.TrackDto

class TrackSearchMapper {

    fun map(list: List<TrackDto>): List<Track> {
        return list.map { dto ->
            Track(
                trackId = dto.trackId,
                trackName = dto.trackName,
                artistName = dto.artistName,
                trackTimeMillis = dto.trackTimeMillis,
                artworkUrl100 = dto.artworkUrl100,
                primaryGenreName = dto.primaryGenreName,
                previewUrl = dto.previewUrl,
                country = dto.country,
                collectionName = dto.collectionName,
                releaseDate = dto.releaseDate
            )
        }
    }
}