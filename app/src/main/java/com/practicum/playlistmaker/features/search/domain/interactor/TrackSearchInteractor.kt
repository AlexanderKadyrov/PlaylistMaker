package com.practicum.playlistmaker.features.search.domain.interactor

import com.practicum.playlistmaker.core.domain.model.Result
import com.practicum.playlistmaker.core.domain.model.Track

interface TrackSearchInteractor {
    fun searchTracks(text: String, consumer: TrackSearchConsumer)

    interface TrackSearchConsumer {
        fun consume(result: Result<List<Track>>)
    }
}