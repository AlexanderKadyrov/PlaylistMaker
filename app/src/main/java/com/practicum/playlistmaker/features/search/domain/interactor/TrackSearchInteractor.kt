package com.practicum.playlistmaker.features.search.domain.interactor

import com.practicum.playlistmaker.core.domain.model.Track

interface TrackSearchInteractor {
    fun searchTracks(text: String, consumer: TrackSearchConsumer)

    interface TrackSearchConsumer {
        fun consume(tracks: List<Track>)
        fun consume(error: String)
    }
}