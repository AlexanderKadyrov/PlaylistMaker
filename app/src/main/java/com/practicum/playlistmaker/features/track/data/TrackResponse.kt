package com.practicum.playlistmaker.features.track.data

import com.practicum.playlistmaker.core.domain.model.Track

data class TrackResponse(
    val results: ArrayList<Track>
)