package com.practicum.playlistmaker.features.player.di

import com.practicum.playlistmaker.di.Creator
import com.practicum.playlistmaker.features.player.data.datastore.AudioPlayerImpl
import com.practicum.playlistmaker.features.player.domain.AudioPlayer
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor
import com.practicum.playlistmaker.features.player.domain.interactor.impl.TrackPlayerInteractorImpl

fun Creator.trackPlayerInteractor(): TrackPlayerInteractor {
    return TrackPlayerInteractorImpl(
        audioPlayer = audioPlayer()
    )
}

private fun Creator.audioPlayer(): AudioPlayer {
    return AudioPlayerImpl()
}