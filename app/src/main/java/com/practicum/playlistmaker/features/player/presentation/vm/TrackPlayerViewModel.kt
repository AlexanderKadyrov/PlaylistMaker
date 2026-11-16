package com.practicum.playlistmaker.features.player.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.update
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor

class TrackPlayerViewModel(
    track: Track,
    private val trackPlayerInteractor: TrackPlayerInteractor
) : ViewModel() {

    private val _trackPlayerState = MutableLiveData(TrackPlayerState())
    val state: LiveData<TrackPlayerState> = _trackPlayerState

    private val _trackModelState = MutableLiveData(track)
    val trackModelState: LiveData<Track> = _trackModelState

    init {
        trackPlayerInteractor.configure(
            url = track.previewUrl,
            consumer = object : TrackPlayerInteractor.TrackPlayerConsumer {
                override fun consumeChangeCurrentPosition(value: String) {
                    _trackPlayerState.update {
                        it.copy(currentPosition = value)
                    }
                }

                override fun consumePrepared() {
                    _trackPlayerState.update {
                        it.copy(isPlayButtonEnabled = true)
                    }
                }

                override fun consumePlaying() {
                    _trackPlayerState.update {
                        it.copy(isPlaying = true)
                    }
                }

                override fun consumePaused() {
                    _trackPlayerState.update {
                        it.copy(isPlaying = false)
                    }
                }

                override fun consumeEnd() {
                    _trackPlayerState.update {
                        it.copy(
                            isPlaying = false,
                            currentPosition = null
                        )
                    }
                }
            }
        )
    }

    fun playbackControl() {
        trackPlayerInteractor.playbackControl()
    }

    fun onPause() {
        trackPlayerInteractor.onPause()
    }

    override fun onCleared() {
        super.onCleared()
        trackPlayerInteractor.onDestroy()
    }
}