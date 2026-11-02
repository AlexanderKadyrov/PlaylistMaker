package com.practicum.playlistmaker.features.search.domain.interactor

import com.practicum.playlistmaker.core.domain.model.Result
import com.practicum.playlistmaker.core.domain.model.Track

interface TrackSearchInteractor {
    fun fetchTrackList(text: String, consumer: TrackSearchConsumer)
    fun fetchStoredTrackList()
    fun store(track: Track)
    fun clearStoredTrackList()

    interface TrackSearchConsumer {
        fun consume(result: Result<List<Track>>)
    }
}