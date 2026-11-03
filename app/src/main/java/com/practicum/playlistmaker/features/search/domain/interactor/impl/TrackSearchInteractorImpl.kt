package com.practicum.playlistmaker.features.search.domain.interactor.impl

import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.features.search.domain.interactor.TrackSearchInteractor
import com.practicum.playlistmaker.features.search.domain.repository.TrackSearchRepository

class TrackSearchInteractorImpl(
    private val repository: TrackSearchRepository
) : TrackSearchInteractor {

    override fun fetchTrackList(
        text: String,
        consumer: TrackSearchInteractor.TrackSearchConsumer
    ) {
        repository.fetchTrackList(text, { result ->
            consumer.consume(result)
        })
    }

    override fun fetchStoredTrackList(): List<Track> {
        return repository.fetchStoredTrackList()
    }

    override fun store(track: Track) {
        repository.store(track)
    }

    override fun clearStoredTrackList() {
        repository.clearStoredTrackList()
    }
}