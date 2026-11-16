package com.practicum.playlistmaker.features.player.domain.interactor.impl

import com.practicum.playlistmaker.core.utils.formatToMinutesAndSeconds
import com.practicum.playlistmaker.features.player.domain.AudioPlayer
import com.practicum.playlistmaker.features.player.domain.AudioPlayerState
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor

class TrackPlayerInteractorImpl(
    private val audioPlayer: AudioPlayer
) : TrackPlayerInteractor {

    override fun configure(
        url: String,
        consumer: TrackPlayerInteractor.TrackPlayerConsumer
    ) {
        audioPlayer.configure(
            url,
            onChangeState = { state ->
                when (state) {
                    AudioPlayerState.PREPARED -> {
                        consumer.consumePrepared()
                    }

                    AudioPlayerState.PLAYING -> {
                        consumer.consumePlaying()
                    }

                    AudioPlayerState.PAUSED -> {
                        consumer.consumePaused()
                    }

                    AudioPlayerState.END -> {
                        consumer.consumeEnd()
                    }

                    else -> {}
                }
            },
            onChangeCurrentPosition = { currentPosition ->
                consumer.consumeChangeCurrentPosition(formatToMinutesAndSeconds(currentPosition))
            }
        )
    }

    override fun playbackControl() {
        audioPlayer.playbackControl()
    }

    override fun onPause() {
        audioPlayer.onPause()
    }

    override fun onDestroy() {
        audioPlayer.onDestroy()
    }
}