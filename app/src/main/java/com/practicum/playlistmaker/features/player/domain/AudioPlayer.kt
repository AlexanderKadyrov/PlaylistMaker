package com.practicum.playlistmaker.features.player.domain

interface AudioPlayer {
    fun configure(
        onChangeState: ((AudioPlayerState) -> Unit),
        onChangeCurrentPosition: ((Int) -> Unit)
    )

    fun prepare(url: String)
    fun playbackControl()
    fun onPause()
    fun onDestroy()
}