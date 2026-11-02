package com.practicum.playlistmaker.features.search.data.network

import com.practicum.playlistmaker.core.domain.model.Track

data class TrackSearchResponse(
    val results: ArrayList<Track>
)