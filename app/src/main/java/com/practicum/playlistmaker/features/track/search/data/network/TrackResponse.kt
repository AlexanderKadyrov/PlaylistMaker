package com.practicum.playlistmaker.features.track.search.data.network

import com.practicum.playlistmaker.core.domain.model.Track

data class TrackResponse(
    val results: ArrayList<Track>
)