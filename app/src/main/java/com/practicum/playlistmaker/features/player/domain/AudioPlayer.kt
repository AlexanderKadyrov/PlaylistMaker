package com.practicum.playlistmaker.features.player.domain

interface AudioPlayer {
    fun configure(
        url: String,
        onChangeState: ((AudioPlayerState) -> Unit),
        onChangeCurrentPosition: ((Int) -> Unit)
    )

    fun playbackControl()
    fun onPause()
    fun onDestroy()
}