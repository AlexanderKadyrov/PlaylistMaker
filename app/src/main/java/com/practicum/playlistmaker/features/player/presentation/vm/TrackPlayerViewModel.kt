package com.practicum.playlistmaker.features.player.presentation.vm

import androidx.lifecycle.ViewModel
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor

class TrackPlayerViewModel(
    private val trackPlayerInteractor: TrackPlayerInteractor
) : ViewModel() {

}