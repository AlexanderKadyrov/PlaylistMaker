package com.practicum.playlistmaker.features.player.di

import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.features.player.data.datastore.AudioPlayerImpl
import com.practicum.playlistmaker.features.player.domain.AudioPlayer
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor
import com.practicum.playlistmaker.features.player.domain.interactor.impl.TrackPlayerInteractorImpl
import com.practicum.playlistmaker.features.player.presentation.vm.TrackPlayerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val playerFeatureModule = module {

    factory<AudioPlayer> {
        AudioPlayerImpl()
    }

    factory<TrackPlayerInteractor> {
        TrackPlayerInteractorImpl(
            audioPlayer = get()
        )
    }

    viewModel { (track: Track) ->
        TrackPlayerViewModel(
            track = track,
            trackPlayerInteractor = get()
        )
    }
}