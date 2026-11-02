package com.practicum.playlistmaker.features.search.domain.interactor.imp

import com.practicum.playlistmaker.core.domain.model.Result
import com.practicum.playlistmaker.features.search.domain.interactor.TrackSearchInteractor
import com.practicum.playlistmaker.features.search.domain.repository.TrackSearchRepository

class TrackSearchInteractorImpl(
    private val repository: TrackSearchRepository
) : TrackSearchInteractor {

    override fun searchTracks(
        text: String,
        consumer: TrackSearchInteractor.TrackSearchConsumer
    ) {
        repository.fetchTrackList(text, { result ->
            when (result) {
                is Result.Success -> {
                    consumer.consume(result.data)
                }

                is Result.Error -> {
                    consumer.consume(text)
                }
            }
        })
    }
}