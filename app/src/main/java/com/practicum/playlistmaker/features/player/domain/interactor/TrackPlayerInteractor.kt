package com.practicum.playlistmaker.features.player.domain.interactor

interface TrackPlayerInteractor {
    fun prepare(url: String)
    fun playbackControl()
    fun onPause()
    fun onDestroy()

    interface TrackPlayerConsumer {
        fun consumeChangeCurrentPosition(value: String)
        fun consumePrepared()
        fun consumePlaying()
        fun consumePaused()
        fun consumeEnd()
    }
}