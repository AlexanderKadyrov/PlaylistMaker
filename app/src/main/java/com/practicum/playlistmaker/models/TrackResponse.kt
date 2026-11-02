package com.practicum.playlistmaker.models

import com.practicum.playlistmaker.core.domain.model.Track

data class TrackResponse(
    val results: ArrayList<Track>
)