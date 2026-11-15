package com.practicum.playlistmaker.features.player.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.postUpdate
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor

class TrackPlayerViewModel(
    val track: Track,
    private val trackPlayerInteractor: TrackPlayerInteractor
) : ViewModel() {

    private val _state = MutableLiveData(TrackPlayerState())
    val state: LiveData<TrackPlayerState> = _state

    init {
        trackPlayerInteractor.configure(
            url = track.previewUrl,
            consumer = object : TrackPlayerInteractor.TrackPlayerConsumer {
                override fun consumeChangeCurrentPosition(value: String) {
                    _state.postUpdate {
                        it.copy(currentPosition = value)
                    }
                }

                override fun consumePrepared() {
                    _state.postUpdate {
                        it.copy(isPlayButtonEnabled = true)
                    }
                }

                override fun consumePlaying() {
                    _state.postUpdate {
                        it.copy(isPlaying = true)
                    }
                }

                override fun consumePaused() {
                    _state.postUpdate {
                        it.copy(isPlaying = false)
                    }
                }

                override fun consumeEnd() {
                    _state.postUpdate {
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