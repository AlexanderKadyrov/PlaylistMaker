package com.practicum.playlistmaker.features.search.di

import com.practicum.playlistmaker.core.data.client.HTTPClient
import com.practicum.playlistmaker.di.Creator
import com.practicum.playlistmaker.features.search.data.mapper.TrackSearchMapper
import com.practicum.playlistmaker.features.search.data.network.TrackSearchService
import com.practicum.playlistmaker.features.search.data.repository.TrackSearchRepositoryImpl
import com.practicum.playlistmaker.features.search.domain.interactor.TrackSearchInteractor
import com.practicum.playlistmaker.features.search.domain.interactor.impl.TrackSearchInteractorImpl
import com.practicum.playlistmaker.features.search.domain.repository.TrackSearchRepository

fun Creator.trackSearchInteractor(): TrackSearchInteractor {
    return TrackSearchInteractorImpl(
        repository = trackSearchRepository()
    )
}

private fun Creator.trackSearchRepository(): TrackSearchRepository {
    return TrackSearchRepositoryImpl(
        localStorage = localStorage,
        client = HTTPClient<TrackSearchService>("https://itunes.apple.com/"),
        mapper = TrackSearchMapper()
    )
}