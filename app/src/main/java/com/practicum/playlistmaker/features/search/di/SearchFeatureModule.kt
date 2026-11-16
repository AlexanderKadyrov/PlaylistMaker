package com.practicum.playlistmaker.features.search.di

import com.practicum.playlistmaker.core.data.client.HTTPClient
import com.practicum.playlistmaker.features.search.data.network.TrackSearchService
import com.practicum.playlistmaker.features.search.data.repository.TrackSearchRepositoryImpl
import com.practicum.playlistmaker.features.search.domain.interactor.TrackSearchInteractor
import com.practicum.playlistmaker.features.search.domain.interactor.impl.TrackSearchInteractorImpl
import com.practicum.playlistmaker.features.search.domain.repository.TrackSearchRepository
import com.practicum.playlistmaker.features.search.presentation.vm.TrackSearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val searchFeatureModule = module {

    single<TrackSearchService> {
        get<Retrofit.Builder>()
            .baseUrl("https://itunes.apple.com/")
            .build()
            .create(TrackSearchService::class.java)
    }

    single<HTTPClient<TrackSearchService>> {
        HTTPClient(service = get<TrackSearchService>())
    }

    single<TrackSearchRepository> {
        TrackSearchRepositoryImpl(
            localStorage = get(),
            client = get(),
            gson = get()
        )
    }

    factory<TrackSearchInteractor> {
        TrackSearchInteractorImpl(
            repository = get()
        )
    }

    viewModel {
        TrackSearchViewModel(
            trackSearchInteractor = get()
        )
    }
}